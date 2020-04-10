package pl.jedro.spaceflysystem.services;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.api.mappers.FlightMapper;
import pl.jedro.spaceflysystem.controllers.FlightController;
import pl.jedro.spaceflysystem.exceptions.DeleteRequestInvalidException;
import pl.jedro.spaceflysystem.exceptions.FlightNotFoundException;
import pl.jedro.spaceflysystem.exceptions.ResourcePresentException;
import pl.jedro.spaceflysystem.exceptions.TouristNotFoundException;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.model.Tourist;
import pl.jedro.spaceflysystem.repositories.FlightRepository;
import pl.jedro.spaceflysystem.repositories.TouristRepository;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class FlightServiceImp implements FlightService {
    private FlightRepository flightRepository;
    private FlightMapper flightMapper;
    private TouristRepository touristRepository;


    public FlightServiceImp(FlightRepository flightRepository, FlightMapper flightMapper, TouristRepository touristRepository) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;
        this.touristRepository = touristRepository;
    }

    @Override
    public FlightDTO getFlightById(Long id) {
        return flightRepository.findById(id).map(flightMapper::flightToDTO).map(flightDTO -> {
            //set API URL
            flightDTO.setFlightUrl(getFlightUrl(id));
            return flightDTO;
        }).orElseThrow(FlightNotFoundException::new);

    }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    @Override
    public FlightDTO createFlight(FlightDTO flightDTO) {
        ExampleMatcher flightMatcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("seatQuantity")
                .withMatcher("arrivalTime", ignoreCase()).withMatcher("departureTime", ignoreCase())
                .withMatcher("ticketPrice", ignoreCase());
        Flight probe = new Flight();
        probe.setTicketPrice(flightDTO.getTicketPrice());
        probe.setArrivalTime(flightDTO.getArrivalTime());

        probe.setDepartureTime(flightDTO.getDepartureTime());
        Example<Flight> example = Example.of(probe, flightMatcher);
        boolean exists = flightRepository.exists(example);
        if (!exists) {
            return saveAndReturnDTO(flightMapper.flightDTOToFlight(flightDTO));
        } else throw new ResourcePresentException("This Flight is already created");
    }


    @Override
    public List<Tourist> getFlightTourists(Long id) {
        return flightRepository.getOne(id).getTourists();
    }

    /**
     * Deletes flight by id. Flight is owner of the relation so method needs to
     * delete all tourist from the flight first, to make deletion from repo possible.
     * @param id flight id
     */
    @Override
    public void deleteFlight(Long id) {
        try {


            for (Tourist t : flightRepository.findById(id).get().getTourists()) {
                deleteTouristInFlight(id, t.getId());
            }
        } catch (NoSuchElementException e) {
            throw new DeleteRequestInvalidException("No Flight with id: " + id + " exists.");
        }
        flightRepository.deleteById(id);
    }

    @Override
    public List<Tourist> addTouristToFlight(Long flightId, Long touristId) {
        Flight savedFlight = flightRepository.findById(flightId).map(flight -> {
            if (touristRepository.findById(touristId).isPresent()) {
                flight.addTourist(touristRepository.findById(touristId).get());
            }
            return flight;
        }).orElseThrow(FlightNotFoundException::new);
        Tourist savedTourist = touristRepository.findById(touristId).map(tourist -> {
            tourist.addFlight(savedFlight);
            return tourist;
        }).orElseThrow(TouristNotFoundException::new);

        flightRepository.save(savedFlight);
        return getFlightTourists(flightId);
    }

    @Override
    public void deleteTouristInFlight(Long flightId, Long touristId) {
        Flight savedFlight = flightRepository.findById(flightId).map(flight -> {
            flight.deleteTourist(touristId);
            return flight;
        }).orElseThrow(FlightNotFoundException::new);
        Tourist savedTourist = touristRepository.findById(touristId).map(tourist -> {
            tourist.deleteFlight(flightId);
            return tourist;
        }).orElseThrow(TouristNotFoundException::new);

        flightRepository.save(savedFlight);
    }


    /**
     * Saves flight and  converts it to DTO.
     *
     * @param flight flight to be saved
     * @return flightDTO created from saved flight
     */
    private FlightDTO saveAndReturnDTO(Flight flight) {
        FlightDTO returnDTO = flightMapper.flightToDTO(flightRepository.save(flight));
        returnDTO.setFlightUrl(getFlightUrl(flight.getId()));
        return returnDTO;

    }

    /**
     * Creates URL based on base URL and flight id.
     *
     * @param id flight id
     * @return created URL
     */
    private String getFlightUrl(Long id) {
        return FlightController.BASE_URL + "/" + id;
    }
}

package pl.jedro.spaceflysystem.services;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.api.mappers.FlightMapper;
import pl.jedro.spaceflysystem.controllers.FlightController;
import pl.jedro.spaceflysystem.exceptions.DeleteRequestInvalidException;
import pl.jedro.spaceflysystem.exceptions.ResourceNotFoundException;
import pl.jedro.spaceflysystem.exceptions.ResourcePresentException;
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
        }).orElseThrow(ResourceNotFoundException::new);

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
        Flight flight = flightRepository.findById(flightId).get();
        Tourist tourist = touristRepository.findById(touristId).get();

        flight.addTourist(tourist);
        tourist.addFlight(flight);

        flightRepository.save(flight);
        return getFlightTourists(flightId);
    }

    @Override
    public void deleteTouristInFlight(Long flightId, Long touristId) {
        Flight flight = flightRepository.findById(flightId).get();
        Tourist tourist = touristRepository.findById(touristId).get();

        tourist.deleteFlight(flightId);
        flight.deleteTourist(touristId);

        flightRepository.save(flight);
    }


    private FlightDTO saveAndReturnDTO(Flight flight) {
        FlightDTO returnDTO = flightMapper.flightToDTO(flightRepository.save(flight));
        returnDTO.setFlightUrl(getFlightUrl(flight.getId()));
        return returnDTO;

    }

    private String getFlightUrl(Long id) {
        return FlightController.BASE_URL + "/" + id;
    }
}

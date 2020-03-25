package pl.jedro.spaceflysystem.services;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.api.mappers.TouristMapper;
import pl.jedro.spaceflysystem.controllers.TouristController;
import pl.jedro.spaceflysystem.exceptions.ResourceNotFoundException;
import pl.jedro.spaceflysystem.exceptions.ResourcePresentException;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.model.Tourist;
import pl.jedro.spaceflysystem.repositories.FlightRepository;
import pl.jedro.spaceflysystem.repositories.TouristRepository;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Service
public class TouristServiceImp implements TouristService {
    private TouristMapper touristMapper;
    private TouristRepository touristsRepository;
    private FlightRepository flightRepository;

    public TouristServiceImp(TouristMapper touristMapper, TouristRepository touristsRepository, FlightRepository flightRepository) {
        this.touristMapper = touristMapper;
        this.touristsRepository = touristsRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Tourist> getAllTourists() {
        return touristsRepository.findAll();
    }

    @Override
    public TouristDTO getTouristById(Long id) {
        return touristsRepository.findById(id).map(touristMapper::touristToDTO).map(touristDTO -> {
            // set Api Url
            touristDTO.setTouristUrl(getTouristUrl(id));
            return touristDTO;
        }).orElseThrow(ResourceNotFoundException::new);

    }

    @Override
    public TouristDTO createTourist(TouristDTO touristDTO) {
        ExampleMatcher touristMatcher = ExampleMatcher.matching().withIgnorePaths("id")
                .withMatcher("name", ignoreCase()).withMatcher("lastName", ignoreCase())
                .withMatcher("dateOfBirth", ignoreCase()).withMatcher("country", ignoreCase());
        Tourist probe = new Tourist();
        probe.setName(touristDTO.getName());
        probe.setCountry(touristDTO.getCountry());
        probe.setDateOfBirth(touristDTO.getDateOfBirth());
        probe.setLastName(touristDTO.getLastName());
        Example<Tourist> example = Example.of(probe, touristMatcher);
        boolean exists = touristsRepository.exists(example);
        if (!exists) {
            return saveAndReturnDTO(touristMapper.touristDTOToTourist(touristDTO));
        } else throw new ResourcePresentException("This tourist is already signed up");
    }


    @Override
    public void deleteTouristById(Long id) {
        touristsRepository.deleteById(id);

    }

    @Override
    public List<Flight> getTouristFlights(Long id) {

        return touristsRepository.getOne(id).getFlights();


    }

    @Override
    public void deleteFlightInTourist(Long touristId, Long flightId) {
        Tourist tourist = touristsRepository.findById(touristId).get();
        Flight flight = flightRepository.findById(flightId).get();

        tourist.deleteFlight(flightId);
        flight.deleteTourist(touristId);

        touristsRepository.save(tourist);
        flightRepository.save(flight);
    }

    @Override
    public List<Flight> addFlightTOTourist(Long touristId, Long flightId) {

        Tourist tourist = touristsRepository.findById(touristId).get();
        Flight flight = flightRepository.findById(flightId).get();
        tourist.addFlight(flight);
        flight.addTourist(tourist);
        touristsRepository.save(tourist);
        flightRepository.save(flight);
        return getTouristFlights(touristId);
    }

    private TouristDTO saveAndReturnDTO(Tourist tourist) {

        TouristDTO returnDTO = touristMapper.touristToDTO(touristsRepository.save(tourist));
        returnDTO.setTouristUrl(getTouristUrl(tourist.getId()));
        return returnDTO;
    }

    private String getTouristUrl(Long id) {
        return TouristController.BASE_URL + "/" + id;
    }
}

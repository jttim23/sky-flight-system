package pl.jedro.spaceflysystem.services;

import org.springframework.stereotype.Service;
import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.api.mappers.FlightMapper;
import pl.jedro.spaceflysystem.api.mappers.TouristMapper;
import pl.jedro.spaceflysystem.exceptions.ResourceNotFoundException;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.model.Tourist;
import pl.jedro.spaceflysystem.repositories.FlightRepository;
import pl.jedro.spaceflysystem.repositories.TouristRepository;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<TouristDTO> getAllTourists() {
        return touristsRepository.findAll().stream()
                .map(tourist -> touristMapper.touristToDTO(tourist)).collect(Collectors.toList());
    }

    @Override
    public TouristDTO getTouristById(Long id) {
        return touristsRepository.findById(id).map(touristMapper::touristToDTO)
                .orElseThrow(ResourceNotFoundException::new);

    }

    @Override
    public TouristDTO createTourist(TouristDTO touristDTO) {
        return saveAndReturnDTO(touristMapper.touristDTOToTourist(touristDTO));
    }

    private TouristDTO saveAndReturnDTO(Tourist tourist) {
        Tourist savedTourist = touristsRepository.save(tourist);
        return touristMapper.touristToDTO(savedTourist);
    }

    @Override
    public void deleteTouristById(Long id) {
        touristsRepository.deleteById(id);

    }

    @Override
    public List<FlightDTO> getTouristFlights(Long id) {
        FlightMapper mapper = FlightMapper.INSTANCE;
        return mapper.listToDTO(touristsRepository.getOne(id).getFlights());


    }

    @Override
    public void deleteFlightInTourist(Long touristId,Long flightId){
        Tourist tourist = touristsRepository.findById(touristId).get();
        Flight flight = flightRepository.findById(flightId).get();

        tourist.deleteFlight(flightId);
        flight.deleteTourist(touristId);

        touristsRepository.save(tourist);
        flightRepository.save(flight);
    }

    @Override
    public List<FlightDTO> addFlightTOTourist(Long touristId, Long flightId) {

        Tourist tourist = touristsRepository.findById(touristId).get();
        Flight flight = flightRepository.findById(flightId).get();
        tourist.addFlight(flight);
        flight.addTourist(tourist);
        touristsRepository.save(tourist);
        flightRepository.save(flight);
        return getTouristFlights(touristId);
    }
}

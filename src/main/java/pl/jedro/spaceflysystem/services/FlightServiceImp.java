package pl.jedro.spaceflysystem.services;

import org.springframework.stereotype.Service;
import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.api.mappers.FlightMapper;
import pl.jedro.spaceflysystem.api.mappers.TouristMapper;
import pl.jedro.spaceflysystem.exceptions.ResourceNotFoundException;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.repositories.FlightRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImp implements FlightService {
    private FlightRepository flightRepository;
    private FlightMapper flightMapper;


    public FlightServiceImp(FlightRepository flightRepository, FlightMapper flightMapper) {
        this.flightRepository = flightRepository;
        this.flightMapper = flightMapper;

    }

    @Override
    public FlightDTO getFlightById(Long id) {
        return flightRepository.findById(id).map(flightMapper::flightToDTO)
                .orElseThrow(ResourceNotFoundException::new);


    }

    @Override
    public Flight getFlightByIdTest(Long id) {
        return flightRepository.findById(id).get();


    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(flight -> flightMapper.flightToDTO(flight)).collect(Collectors.toList());
    }

    @Override
    public FlightDTO createFlight(FlightDTO flightDTO) {
        return saveAndReturnDTO(flightMapper.flightDTOToFlight(flightDTO));
    }

    private FlightDTO saveAndReturnDTO(Flight flight) {
        Flight savedFlight = flightRepository.save(flight);
        return flightMapper.flightToDTO(savedFlight);

    }

    @Override
    public List<TouristDTO> getFlightTourists(Long id) {
        TouristMapper mapper = TouristMapper.INSTANCE;
        return mapper.listToDTO(flightRepository.getOne(id).getTourists());
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);

    }

    @Override
    public FlightDTO addTouristByFlightId() {
        return null;
    }

    @Override
    public FlightDTO deleteTouristByFlightId() {
        return null;
    }
}

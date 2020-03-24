package pl.jedro.spaceflysystem.services;

import org.springframework.stereotype.Service;
import pl.jedro.spaceflysystem.api.DTO.FlightDTO;

import pl.jedro.spaceflysystem.api.mappers.FlightMapper;
import pl.jedro.spaceflysystem.controllers.FlightController;
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
        FlightDTO returnDTO= flightRepository.findById(id).map(flightMapper::flightToDTO)
                .orElseThrow(RuntimeException::new);
        returnDTO.setFlightUrl(FlightController.BASE_URL+"/"+id);
        return returnDTO;
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll().stream().map(flight -> {
            FlightDTO flightDTO = flightMapper.flightToDTO(flight);
            flightDTO.setFlightUrl("/api/v1/flights/" + flight.getId());
            return flightDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public FlightDTO createFlight(FlightDTO flightDTO) {
        return saveAndReturnDTO(flightMapper.flightDTOToFlight(flightDTO));
    }

    private FlightDTO saveAndReturnDTO(Flight flight) {
        Flight savedFlight = flightRepository.save(flight);
        FlightDTO flightDTO = flightMapper.flightToDTO(savedFlight);
        flightDTO.setFlightUrl("/api/v1/flights/" + savedFlight.getId());
        return flightDTO;
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

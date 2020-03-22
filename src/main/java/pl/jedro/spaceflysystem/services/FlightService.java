package pl.jedro.spaceflysystem.services;

import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.api.DTO.TouristDTOList;

import java.util.List;

public interface FlightService {

    List<FlightDTO> getAllFlights();

    FlightDTO createFlight(FlightDTO flightDTO);

    void deleteFlight(Long id);

    FlightDTO getFlightById(Long id);

    FlightDTO addTouristByFlightId();

    FlightDTO deleteTouristByFlightId();

}

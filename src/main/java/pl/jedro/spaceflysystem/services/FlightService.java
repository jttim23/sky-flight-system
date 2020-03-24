package pl.jedro.spaceflysystem.services;

import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.api.DTO.FlightDTOExtended;

import java.util.List;

public interface FlightService {

    List<FlightDTO> getAllFlights();

    FlightDTO createFlight(FlightDTO flightDTO);

    void deleteFlight(Long id);

    FlightDTOExtended getFlightById(Long id);

    FlightDTO addTouristByFlightId();

    FlightDTO deleteTouristByFlightId();

}

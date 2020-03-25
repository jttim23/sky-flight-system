package pl.jedro.spaceflysystem.services;

import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.model.Flight;

import java.util.List;

public interface FlightService {

    List<FlightDTO> getAllFlights();

    FlightDTO createFlight(FlightDTO flightDTO);

    void deleteFlight(Long id);

    List<TouristDTO> getFlightTourists(Long id);

    FlightDTO getFlightById(Long id);

    Flight getFlightByIdTest(Long id);

    List<TouristDTO> addTouristToFlight(Long flightId, Long touristId);

    FlightDTO deleteTouristByFlightId();

}

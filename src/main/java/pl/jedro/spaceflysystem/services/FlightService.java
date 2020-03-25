package pl.jedro.spaceflysystem.services;

import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.model.Tourist;

import java.util.List;

public interface FlightService {

    List<Flight> getAllFlights();

    FlightDTO createFlight(FlightDTO flightDTO);

    void deleteFlight(Long id);

    List<Tourist> getFlightTourists(Long id);

    FlightDTO getFlightById(Long id);


    List<Tourist> addTouristToFlight(Long flightId, Long touristId);

    void deleteTouristInFlight(Long flightId, Long touristId);

}

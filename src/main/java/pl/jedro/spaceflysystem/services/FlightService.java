package pl.jedro.spaceflysystem.services;

import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.exceptions.DeleteRequestInvalidException;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.model.Tourist;

import java.util.List;

public interface FlightService {

    List<Flight> getAllFlights();

    FlightDTO getFlightById(Long id);

    List<Tourist> getFlightTourists(Long id);

    FlightDTO createFlight(FlightDTO flightDTO);

    List<Tourist> addTouristToFlight(Long flightId, Long touristId);

    void deleteFlight(Long id);

    void deleteTouristInFlight(Long flightId, Long touristId) throws DeleteRequestInvalidException;

}

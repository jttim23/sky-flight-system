package pl.jedro.spaceflysystem.services;

import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.model.Tourist;

import java.util.List;

/**
 * Service for Flights.
 */
public interface FlightService {

    /**
     * Gets all flights.
     *
     * @return list of all flights
     */
    List<Flight> getAllFlights();

    /**
     * Finds flight with specific id.
     *
     * @param id flight id
     * @return found flight mapped to DTO
     */
    FlightDTO getFlightById(Long id);

    /**
     * Finds flight with specific id and gets list of its tourists.
     *
     * @param id flight id
     * @return list of tourists assigned to flight
     */
    List<Tourist> getFlightTourists(Long id);

    /**
     * Saves flight in db.
     *
     * @param flightDTO flight to save
     * @return persisted flight
     */
    FlightDTO createFlight(FlightDTO flightDTO);

    /**
     * Adds tourist with specific id to list of tourists in flight with specific id.
     *
     * @param flightId  flight id
     * @param touristId tourist id
     * @return list of tourists assigned to flight after adding
     */
    List<Tourist> addTouristToFlight(Long flightId, Long touristId);

    /**
     * Deletes flight with specific id.
     *
     * @param id flight id
     */
    void deleteFlight(Long id);

    /**
     * Deletes tourist with specific id from list of tourists in flight with specific id.
     *
     * @param flightId  flight id
     * @param touristId tourist id
     */
    void deleteTouristInFlight(Long flightId, Long touristId);

}

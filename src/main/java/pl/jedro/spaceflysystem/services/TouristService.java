package pl.jedro.spaceflysystem.services;

import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.model.Tourist;

import java.util.List;

/**
 * Service fot Tourists.
 */
public interface TouristService {
    /**
     * Gets all tourists.
     *
     * @return list of all tourists
     */
    List<Tourist> getAllTourists();

    /**
     * Finds tourist with specific id.
     *
     * @param id tourist id
     * @return found tourist mapped to DTO
     */
    TouristDTO getTouristById(Long id);

    /**
     * Finds tourist with specific id and gets list of its flights.
     *
     * @param id tourist id
     * @return list of flights assigned to tourist
     */
    List<Flight> getTouristFlights(Long id);

    /**
     * Saves tourists in db.
     *
     * @param touristDTO tourist to save
     * @return persisted tourists
     */
    TouristDTO createTourist(TouristDTO touristDTO);

    /**
     * Adds flight with specific id to list of flights in tourist with specific id.
     *
     * @param touristId tourist id
     * @param flightId  flight id
     * @return list of flight assigned to tourist after adding
     */
    List<Flight> addFlightToTourist(Long touristId, Long flightId);

    /**
     * Deletes tourist with specific id
     *
     * @param id tourist id
     */
    void deleteTourist(Long id);

    /**
     * Deletes flight with specific id from list of flights in tourist with specific id.
     *
     * @param touristId tourist id
     * @param flightId  flight id
     */
    void deleteFlightInTourist(Long touristId, Long flightId);


}

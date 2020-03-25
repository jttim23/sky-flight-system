package pl.jedro.spaceflysystem.services;

import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.model.Tourist;

import java.util.List;

public interface TouristService {
    List<Tourist> getAllTourists();

    TouristDTO getTouristById(Long id);

    List<Flight> getTouristFlights(Long id);

    TouristDTO createTourist(TouristDTO touristDTO);

    void deleteTouristById(Long id);

    void deleteFlightInTourist(Long id, Long flightId);

    List<Flight> addFlightTOTourist(Long touristId, Long flightId);

}

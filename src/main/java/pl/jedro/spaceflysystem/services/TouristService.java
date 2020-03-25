package pl.jedro.spaceflysystem.services;

import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;

import java.util.List;

public interface TouristService {
    List<TouristDTO> getAllTourists();

    TouristDTO getTouristById(Long id);

    List<FlightDTO> getTouristFlights(Long id);

    TouristDTO createTourist(TouristDTO touristDTO);

    void deleteTouristById(Long id);

    TouristDTO deleteFlightByTouristId(Long id);

    List<FlightDTO> addFlightByTouristId(Long touristId, Long flightId);

}

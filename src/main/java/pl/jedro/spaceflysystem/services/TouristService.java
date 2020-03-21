package pl.jedro.spaceflysystem.services;

import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.model.Tourist;

import java.util.List;

public interface TouristService {
    List<TouristDTO> getAllTourists();

    TouristDTO getTouristById(Long id);

    TouristDTO createTourist(TouristDTO touristDTO);

    void deleteTouristById(Long id);

    TouristDTO deleteFlightByTouristId(Long id);

    TouristDTO addFlightByTouristId(Long id);

}

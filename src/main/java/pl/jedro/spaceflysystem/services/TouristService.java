package pl.jedro.spaceflysystem.services;

import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.model.Tourist;

import java.util.List;

public interface TouristService {
    List<Tourist> getAllTourists();
    TouristDTO getTouristById(Long id);
    TouristDTO createTourist(TouristDTO touristDTO);
    void deleteTouristById(Long id);
    TouristDTO saveTourist(Long id, TouristDTO touristDTO);

}

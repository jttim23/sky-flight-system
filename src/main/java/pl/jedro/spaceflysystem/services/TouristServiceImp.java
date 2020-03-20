package pl.jedro.spaceflysystem.services;

import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.api.mappers.TouristMapper;
import pl.jedro.spaceflysystem.model.Tourist;
import pl.jedro.spaceflysystem.repositories.TouristsRepository;

import java.util.List;

public class TouristServiceImp implements TouristService {
    private TouristMapper touristMapper;
    private TouristsRepository repository;

    public TouristServiceImp(TouristMapper touristMapper, TouristsRepository repository) {
        this.touristMapper = touristMapper;
        this.repository = repository;
    }

    @Override
    public List<Tourist> getAllTourists() {
        return null;
    }

    @Override
    public TouristDTO getTouristById(Long id) {
        return null;
    }

    @Override
    public TouristDTO createTourist(TouristDTO touristDTO) {
        return null;
    }

    @Override
    public void deleteTouristById(Long id) {

    }

    @Override
    public TouristDTO saveTourist(Long id, TouristDTO touristDTO) {
        return null;
    }
}

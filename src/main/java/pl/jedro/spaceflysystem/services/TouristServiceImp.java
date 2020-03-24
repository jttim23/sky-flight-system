package pl.jedro.spaceflysystem.services;

import org.springframework.stereotype.Service;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.api.DTO.TouristDTOExtended;
import pl.jedro.spaceflysystem.api.mappers.TouristExtMapper;
import pl.jedro.spaceflysystem.api.mappers.TouristMapper;
import pl.jedro.spaceflysystem.controllers.TouristController;
import pl.jedro.spaceflysystem.exceptions.ResourceNotFoundException;
import pl.jedro.spaceflysystem.model.Tourist;
import pl.jedro.spaceflysystem.repositories.TouristRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TouristServiceImp implements TouristService {
    private TouristMapper touristMapper;
    private TouristRepository touristsRepository;


    public TouristServiceImp(TouristMapper touristMapper,
                             TouristRepository touristsRepository) {
        this.touristMapper = touristMapper;
        this.touristsRepository = touristsRepository;

    }

    @Override
    public List<TouristDTO> getAllTourists() {
        return touristsRepository.findAll().stream()
                .map(tourist -> touristMapper.touristToDTO(tourist)).collect(Collectors.toList());
    }

    @Override
    public TouristDTOExtended getTouristById(Long id) {
        return touristsRepository.findById(id).map(touristMapper::touristToDTOExt)
                .orElseThrow(ResourceNotFoundException::new);

    }

    @Override
    public TouristDTO createTourist(TouristDTO touristDTO) {
        return saveAndReturnDTO(touristMapper.touristDTOToTourist(touristDTO));
    }

    private TouristDTO saveAndReturnDTO(Tourist tourist) {
        Tourist savedTourist = touristsRepository.save(tourist);
        return touristMapper.touristToDTO(savedTourist);
    }

    @Override
    public void deleteTouristById(Long id) {
        touristsRepository.deleteById(id);

    }

    @Override
    public TouristDTO deleteFlightByTouristId(Long id) {
        return null;
    }

    @Override
    public TouristDTO addFlightByTouristId(Long id) {
        return null;
    }
}

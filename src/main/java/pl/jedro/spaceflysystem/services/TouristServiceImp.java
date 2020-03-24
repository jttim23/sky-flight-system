package pl.jedro.spaceflysystem.services;

import org.springframework.stereotype.Service;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.api.mappers.TouristMapper;
import pl.jedro.spaceflysystem.controllers.TouristController;
import pl.jedro.spaceflysystem.model.Tourist;
import pl.jedro.spaceflysystem.repositories.TouristRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TouristServiceImp implements TouristService {
    private TouristMapper touristMapper;
    private TouristRepository touristsRepository;

    public TouristServiceImp(TouristRepository repository, TouristMapper touristMapper) {
        this.touristMapper = touristMapper;
        this.touristsRepository = repository;
    }

    @Override
    public List<TouristDTO> getAllTourists() {
        return touristsRepository.findAll().stream().map(tourist -> {
            TouristDTO touristDTO = touristMapper.touristToDTO(tourist);
            touristDTO.setTouristUrl("/api/v1/tourists/" + tourist.getId());
            return touristDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public TouristDTO getTouristById(Long id) {
        TouristDTO returnDTO = touristsRepository.findById(id).map(touristMapper::touristToDTO)
                .orElseThrow(RuntimeException::new);
        returnDTO.setTouristUrl(TouristController.BASE_URL+"/"+id);
        return returnDTO;
    }

    @Override
    public TouristDTO createTourist(TouristDTO touristDTO) {
        return saveAndReturnDTO(touristMapper.touristDTOToTourist(touristDTO));
    }

    private TouristDTO saveAndReturnDTO(Tourist tourist) {
        Tourist savedTourist = touristsRepository.save(tourist);
        TouristDTO returnDTO = touristMapper.touristToDTO(savedTourist);
        returnDTO.setTouristUrl("/api/v1/customers/" + savedTourist.getId());
        return returnDTO;
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

package pl.jedro.spaceflysystem.services;

import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.api.mappers.TouristMapper;
import pl.jedro.spaceflysystem.model.Tourist;
import pl.jedro.spaceflysystem.repositories.TouristsRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TouristServiceImp implements TouristService {
    private TouristMapper touristMapper;
    private TouristsRepository touristsRepository;

    public TouristServiceImp(TouristMapper touristMapper, TouristsRepository repository) {
        this.touristMapper = touristMapper;
        this.touristsRepository = repository;
    }

    @Override
    public List<TouristDTO> getAllTourists() {
        return touristsRepository.findAll().stream().map(tourist -> {
            TouristDTO touristDTO = touristMapper.touristToTouristDTO(tourist);
            touristDTO.setTouristUrl("/api/v1/tourists/" + tourist.getId());
            return touristDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public TouristDTO getTouristById(Long id) {
        return touristsRepository.findById(id).map(touristMapper::touristToTouristDTO)
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public TouristDTO createTourist(TouristDTO touristDTO) {
        return saveAndReturnDTO(touristMapper.touristDTOToTourist(touristDTO)) ;
    }
    private TouristDTO saveAndReturnDTO(Tourist tourist){
        Tourist savedTourist = touristsRepository.save(tourist);
        TouristDTO returnDTO = touristMapper.touristToTouristDTO(savedTourist);
        returnDTO.setTouristUrl("/api/v1/customers/"+savedTourist.getId());
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

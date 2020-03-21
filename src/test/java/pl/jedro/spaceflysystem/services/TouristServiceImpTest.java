package pl.jedro.spaceflysystem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.api.mappers.TouristMapper;
import pl.jedro.spaceflysystem.model.Tourist;
import pl.jedro.spaceflysystem.repositories.TouristsRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TouristServiceImpTest {
    @Mock
    TouristsRepository touristsRepository;
    TouristMapper touristMapper = TouristMapper.INSTANCE;
    TouristService touristService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        touristService = new TouristServiceImp(touristMapper, touristsRepository);

    }

    @Test
    void getAllTourists() {
        Tourist tourist1 = new Tourist();
        tourist1.setId(1L);
        tourist1.setName("Jimmy");
        Tourist tourist2 = new Tourist();
        tourist2.setId(2L);
        tourist2.setName("Eve");

        when(touristsRepository.findAll()).thenReturn(Arrays.asList(tourist1,tourist2));
        List<TouristDTO> touristDTOS = touristService.getAllTourists();
        assertEquals(2,touristDTOS.size());
    }

    @Test
    void getTouristById() {
        Tourist tourist = new Tourist();
        tourist.setId(1L);
        tourist.setName("Jimmy");



        when(touristsRepository.findById(anyLong())).thenReturn(Optional.ofNullable(tourist));
        TouristDTO touristDTO = touristService.getTouristById(1L);
        assertEquals("Jimmy", touristDTO.getName());
    }
    @Test
    void createTourist(){
        TouristDTO touristDTO = new TouristDTO();
        touristDTO.setName("Jimmy");

        Tourist savedTourist= new Tourist();
        savedTourist.setName(touristDTO.getName());
        savedTourist.setId(1L);

        when(touristsRepository.save(any(Tourist.class))).thenReturn(savedTourist);

        TouristDTO savedDTO = touristService.createTourist(touristDTO);

        assertEquals(savedDTO.getName(),savedTourist.getName());
        assertEquals("/api/v1/customers/1",savedDTO.getTouristUrl());

    }
    @Test
    void deleteTouristById(){
        Long id = 1L;
        touristsRepository.deleteById(id);
        verify(touristsRepository, times(1)).deleteById(anyLong());
    }
    @Test
    void deleteFlightByTouristId(){
        //not implemented yet
    }
    @Test
    void addFlightByTouristId(){
        //not implemented yet
    }
}
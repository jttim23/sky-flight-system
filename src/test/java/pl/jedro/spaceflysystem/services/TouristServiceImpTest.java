package pl.jedro.spaceflysystem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.api.mappers.TouristMapper;
import pl.jedro.spaceflysystem.model.Tourist;
import pl.jedro.spaceflysystem.repositories.TouristsRepository;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class TouristServiceImpTest {
    @Mock
    TouristsRepository touristsRepository;
    TouristMapper touristMapper = TouristMapper.INSTANCE;
    TouristService touristService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        touristService = new TouristServiceImp(touristMapper,touristsRepository);

    }

    @Test
    void getTouristById() {
        Tourist tourist = new Tourist();
        tourist.setId(1L);
        tourist.setName("Jimmy");
        tourist.setLastName("Moriarty");


        when(touristsRepository.findById(anyLong())).thenReturn(Optional.ofNullable(tourist));
        TouristDTO touristDTO = touristService.getTouristById(1L);
        assertEquals("Jimmy",touristDTO.getName());
    }

}
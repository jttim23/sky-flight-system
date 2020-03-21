package pl.jedro.spaceflysystem.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.jedro.spaceflysystem.api.DTO.FlightDTO;

import pl.jedro.spaceflysystem.api.mappers.FlightMapper;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.model.Tourist;
import pl.jedro.spaceflysystem.repositories.FlightRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


class FlightServiceImpTest {
    FlightService flightService;
    FlightMapper flightMapper=FlightMapper.INSTANCE;
    @Mock
    FlightRepository flightRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        flightService=new FlightServiceImp(flightRepository,flightMapper);
    }
    @Test
    void getAllFlights()throws Exception {
        Flight flight1 = new Flight();
        flight1.setSeatQuantity(6);
        flight1.setId(1L);
        Flight flight2 = new Flight();
        flight2.setSeatQuantity(5);
        flight2.setId(2L);

        when(flightRepository.findAll()).thenReturn(Arrays.asList(flight1,flight2));
        List<FlightDTO> flightDTOS = flightService.getAllFlights();
        assertEquals(2,flightDTOS.size());

    }
    @Test
    void createFlight()throws Exception{

        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setSeatQuantity(6);
        Flight savedFlight = new Flight();
        savedFlight.setSeatQuantity(flightDTO.getSeatQuantity());
        savedFlight.setId(1L);
        when(flightRepository.save(any(Flight.class))).thenReturn(savedFlight);
        FlightDTO savedDTO = flightService.createFlight(flightDTO);
        assertEquals(savedFlight.getSeatQuantity(),savedDTO.getSeatQuantity());
        assertEquals("/api/v1/flights/1",savedDTO.getFlightUrl());
    }
    @Test
    void deleteFlight()throws Exception{
        Long id = 1L;
        flightRepository.deleteById(id);
       verify(flightRepository,times(1)).deleteById(anyLong());
    }
    @Test
    void addTouristByFlightId() throws Exception{
        Tourist tourist1 = new Tourist();
        tourist1.setId(1L);
        tourist1.setName("Jimmy");
        Tourist tourist2 = new Tourist();
        tourist2.setId(2L);
        tourist2.setName("Eve");
    }

}
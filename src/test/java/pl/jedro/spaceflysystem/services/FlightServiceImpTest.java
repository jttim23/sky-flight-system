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
import pl.jedro.spaceflysystem.repositories.TouristRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class FlightServiceImpTest {
    FlightService flightService;
    FlightMapper flightMapper = FlightMapper.INSTANCE;

    @Mock
    FlightRepository flightRepository;
    @Mock
    TouristRepository touristRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        flightService = new FlightServiceImp(flightRepository, flightMapper,touristRepository);
    }

    @Test
    void getAllFlights() throws Exception {
        Flight flight1 = new Flight();
        flight1.setSeatQuantity(6);
        flight1.setId(1L);
        Flight flight2 = new Flight();
        flight2.setSeatQuantity(5);
        flight2.setId(2L);

        when(flightRepository.findAll()).thenReturn(Arrays.asList(flight1, flight2));
        List<FlightDTO> flightDTOS = flightService.getAllFlights();
        assertEquals(2, flightDTOS.size());

    }

    @Test
    void getFlightById() throws Exception {
        Tourist tourist1 = new Tourist();
        tourist1.setId(1L);
        tourist1.setName("Jimmy");
        Tourist tourist2 = new Tourist();
        tourist2.setId(2L);
        tourist2.setName("Eve");
        List<Tourist> tourists = Arrays.asList(tourist1, tourist2);
        Flight flight = new Flight();
        flight.setSeatQuantity(6);
        flight.setId(1L);
        flight.setTourists(tourists);
        when(flightRepository.findById(anyLong())).thenReturn(Optional.of(flight));
        FlightDTO flightDTO = flightService.getFlightById(1L);
        assertEquals(6, flightDTO.getSeatQuantity());
    }


    @Test
    void createFlight() throws Exception {

        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setSeatQuantity(6);
        Flight savedFlight = new Flight();
        savedFlight.setSeatQuantity(flightDTO.getSeatQuantity());
        savedFlight.setId(1L);
        when(flightRepository.save(any(Flight.class))).thenReturn(savedFlight);
        FlightDTO savedDTO = flightService.createFlight(flightDTO);
        assertEquals(savedFlight.getSeatQuantity(), savedDTO.getSeatQuantity());
    }

    @Test
    void deleteFlight() throws Exception {
        Long id = 1L;
        flightRepository.deleteById(id);
        verify(flightRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void addTouristByFlightId() throws Exception {
        Tourist tourist1 = new Tourist();
        tourist1.setId(1L);
        tourist1.setName("Jimmy");
        Tourist tourist2 = new Tourist();
        tourist2.setId(2L);
        tourist2.setName("Eve");
    }

}
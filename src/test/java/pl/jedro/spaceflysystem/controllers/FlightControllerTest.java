package pl.jedro.spaceflysystem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.services.FlightService;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FlightControllerTest extends AbstractRestControllerTest {
    @Mock
    private FlightService flightService;
    @InjectMocks
    private FlightController flightController;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(flightController).setControllerAdvice(new RuntimeException()).build();
    }

    @Test
    void getAllFlights() throws Exception {
        FlightDTO flight1 = new FlightDTO();
        flight1.setSeatQuantity(6);

        FlightDTO flight2 = new FlightDTO();
        flight2.setSeatQuantity(5);


        when(flightService.getAllFlights()).thenReturn(Arrays.asList(flight1, flight2));

        mockMvc.perform(get(FlightController.BASE_URL).accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void deleteFlightById() throws Exception {

        mockMvc.perform(delete(FlightController.BASE_URL + "/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
        verify(flightService, times(1)).deleteFlight(anyLong());
    }

    @Test
    void createFlight() throws Exception {
        FlightDTO flightDTO = new FlightDTO();

        flightDTO.setSeatQuantity(5);

        FlightDTO returnDto = new FlightDTO();
        returnDto.setSeatQuantity(flightDTO.getSeatQuantity());
        when(flightService.createFlight(any())).thenReturn(returnDto);

        mockMvc.perform(post(FlightController.BASE_URL).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(asJsonString(flightDTO)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.seatQuantity", equalTo(5)));
    }
}
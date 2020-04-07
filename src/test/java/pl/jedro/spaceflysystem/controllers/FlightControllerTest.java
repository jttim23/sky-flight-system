package pl.jedro.spaceflysystem.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.services.FlightService;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlightController.class)
class FlightControllerTest extends AbstractRestControllerTest {
    @MockBean
    private FlightService flightService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllFlights() throws Exception {
        Flight flight1 = new Flight();
        flight1.setSeatQuantity(6);

        Flight flight2 = new Flight();
        flight2.setSeatQuantity(5);


        when(flightService.getAllFlights()).thenReturn(Arrays.asList(flight1, flight2));

        mockMvc.perform(get(FlightController.BASE_URL).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void deleteFlight() throws Exception {

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
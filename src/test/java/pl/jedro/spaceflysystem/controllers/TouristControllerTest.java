package pl.jedro.spaceflysystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.model.Tourist;
import pl.jedro.spaceflysystem.services.TouristService;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TouristController.class)
class TouristControllerTest {
    @MockBean
    private TouristService touristService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;


    @Test
    void getAllTourists() throws Exception {
        Tourist tourist1 = new Tourist();
        tourist1.setName("Jimmy");

        Tourist tourist2 = new Tourist();
        tourist2.setName("Eve");

        when(touristService.getAllTourists()).thenReturn(Arrays.asList(tourist1, tourist2));

        mockMvc.perform(get(TouristController.BASE_URL).accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    void createTourist() throws Exception {
        TouristDTO touristDTO = new TouristDTO();
        touristDTO.setName("Jimmy");


        TouristDTO returnDTO = new TouristDTO();

        returnDTO.setName(touristDTO.getName());
        when(touristService.createTourist(any())).thenReturn(returnDTO);

        mockMvc.perform(post(TouristController.BASE_URL).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsBytes(touristDTO)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.name", equalTo("Jimmy")));
    }

    @Test
    void deleteTourist() throws Exception {
        mockMvc.perform(delete(TouristController.BASE_URL + "/1").
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isFound());
        verify(touristService, times(1)).deleteTourist(anyLong());
    }


    @Test
    void getTouristFlights() throws Exception {
        Flight flight = new Flight();
        flight.setSeatQuantity(6);
        Tourist tourist1 = new Tourist();
        tourist1.setName("Jimmy");
        tourist1.setId(1L);
        tourist1.setFlights(Collections.singletonList(flight));
        when(touristService.getTouristFlights(anyLong())).thenReturn(tourist1.getFlights());

        mockMvc.perform(get(TouristController.BASE_URL + "/1/flights")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].seatQuantity", equalTo(6)));
    }


    @Test
    void getTouristById() throws Exception{
        TouristDTO touristDTO = new TouristDTO();
        touristDTO.setName("Jimmy");
        when(touristService.getTouristById(anyLong())).thenReturn(touristDTO);

        mockMvc.perform(get(TouristController.BASE_URL+"/1")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name", equalTo("Jimmy")));
    }


    @Test
    void addFlightToTourist() throws Exception {
        Tourist tourist1 = new Tourist();
        tourist1.setId(1L);
        tourist1.setName("Jimmy");
        Flight flight1 = new Flight();
        flight1.setId(2L);
        flight1.setSeatQuantity(6);
        tourist1.setFlights(Collections.singletonList(flight1));
        when(touristService.addFlightToTourist(anyLong(),anyLong())).thenReturn(tourist1.getFlights());
        mockMvc.perform(post(TouristController.BASE_URL+"/1/flights")
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).param("flightId","2"))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.[0].seatQuantity", equalTo(6)));
    }


    @Test
    void deleteFlightInTourist() throws Exception {
        mockMvc.perform(delete(TouristController.BASE_URL+"/1/flights")
                .contentType(MediaType.APPLICATION_JSON).param("flightId","2"))
                .andExpect(status().isFound());
        verify(touristService, times(1)).deleteFlightInTourist(anyLong(),anyLong());
    }
}
package pl.jedro.spaceflysystem.controllers;

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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TouristController.class)
class TouristControllerTest extends AbstractRestControllerTest {
    @MockBean
    private TouristService touristService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void addFlightTest() throws Exception {

        Flight flight1 = new Flight();
        flight1.setSeatQuantity(6);

        Flight flight2 = new Flight();
        flight2.setSeatQuantity(5);

        when(touristService.addFlightTOTourist(anyLong(), anyLong())).thenReturn(Arrays.asList(flight1, flight2));
        mockMvc.perform(post(TouristController.BASE_URL + "/2/flights").param("flight_id", String.valueOf(1L))
                .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(jsonPath("$", hasSize(2)));

    }

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

        mockMvc.perform(post(TouristController.BASE_URL).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(asJsonString(touristDTO)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.name", equalTo("Jimmy")));
    }

    @Test
    void deleteTourist() throws Exception {
        mockMvc.perform(delete(TouristController.BASE_URL + "/1").
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isFound());
        verify(touristService, times(1)).deleteTourist(anyLong());
    }
}
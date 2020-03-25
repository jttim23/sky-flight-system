package pl.jedro.spaceflysystem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.services.TouristService;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TouristControllerTest extends AbstractRestControllerTest {
    @Mock
    private TouristService touristService;
    @InjectMocks
    private TouristController touristController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(touristController)
                .setControllerAdvice(new RuntimeException()).build();
    }

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

//    @Test
//    void getAllTourists() throws Exception {
//        TouristDTO tourist1 = new TouristDTO();
//        tourist1.setName("Jimmy");
//
//        TouristDTO tourist2 = new TouristDTO();
//        tourist2.setName("Eve");
//
//        when(touristService.getAllTourists()).thenReturn(Arrays.asList(tourist1, tourist2));
//
//        mockMvc.perform(get(TouristController.BASE_URL).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));

    //}

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
        verify(touristService, times(1)).deleteTouristById(anyLong());
    }
}
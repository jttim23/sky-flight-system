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

    //    @Test
//    void getAllTourists() throws Exception{
//        TouristDTO tourist1 = new TouristDTO();
//        tourist1.setName("Jimmy");
//        tourist1.setTouristUrl(TouristController.BASE_URL+"/1");
//        TouristDTO tourist2 = new TouristDTO();
//        tourist2.setName("Eve");
//        tourist2.setTouristUrl(TouristController.BASE_URL+"/2");
//        when(touristService.getAllTourists()).thenReturn(Arrays.asList(tourist1,tourist2));
//
//        mockMvc.perform(get(TouristController.BASE_URL).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andExpect(jsonPath("$",hasSize(2)));
//
//    }
    @Test
    void createTourist() throws Exception {
        TouristDTO touristDTO = new TouristDTO();
        touristDTO.setName("Jimmy");
        touristDTO.setTouristUrl(TouristController.BASE_URL + "/1");

        TouristDTO returnDTO = new TouristDTO();
        returnDTO.setTouristUrl(touristDTO.getTouristUrl());
        returnDTO.setName(touristDTO.getName());
        when(touristService.createTourist(any())).thenReturn(returnDTO);

        mockMvc.perform(post(TouristController.BASE_URL).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(asJsonString(touristDTO)))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.name", equalTo("Jimmy")))
                .andExpect(jsonPath("$.touristUrl", equalTo(TouristController.BASE_URL + "/1")));
    }

    @Test
    void deleteTourist() throws Exception {
        mockMvc.perform(delete(TouristController.BASE_URL + "/1").
                contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
        verify(touristService, times(1)).deleteTouristById(anyLong());
    }
}
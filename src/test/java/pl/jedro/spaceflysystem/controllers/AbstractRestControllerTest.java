package pl.jedro.spaceflysystem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRestControllerTest {
public static String asJsonString(final Object object){
    try {
        return new ObjectMapper().writeValueAsString(object);

    } catch (Exception e){
        throw new RuntimeException();
    }
}
}

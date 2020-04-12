package pl.jedro.spaceflysystem.controllers;


import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.jedro.spaceflysystem.SpaceFlySystemApplication;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpaceFlySystemApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TouristIT {
    @Value("${local.server.port}")
    private int ports;


    @BeforeAll
    public void setUp() {
        port = ports;
        baseURI = "http://localhost" + TouristController.BASE_URL; // Will result in "http://localhost:xxxx/..."

    }

    @Test
    void sampleGetTest() {
        get(baseURI + "/1").then().statusCode(200).assertThat().body("name", is("Saba"));
    }

    @Test
    void deleteTouristFlightTest() throws InterruptedException {
        ValidatableResponse response = delete(baseURI + "/1/flights?flightId=99").then().statusCode(302);
        response = delete(baseURI + "/1/flights?flightId=99").then().statusCode(400);

    }

    @Test
    void postTouristFlightTest() throws InterruptedException {
        ValidatableResponse response = post(baseURI + "/1/flights?flightId=19").then().statusCode(201);
        response = post(baseURI + "/1/flights?flightId=19").then().statusCode(409);
    }

    @Test
    void deleteTouristTest() throws InterruptedException {
        ValidatableResponse response = delete(baseURI + "/2").then().statusCode(302);
        response = get(baseURI + "/2").then().statusCode(404);
    }
}
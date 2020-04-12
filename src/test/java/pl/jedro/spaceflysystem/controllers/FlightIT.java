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
public class FlightIT {


    @Value("${local.server.port}")
    private int ports;


    @BeforeAll
    public void setUp() {
        port = ports;
        baseURI = "http://localhost" + FlightController.BASE_URL; // Will result in "http://localhost:xxxx/..."

    }

    @Test
    void sampleGetTest() {
        get(baseURI + "/1").then().statusCode(200).assertThat().body("seatQuantity", is(8));
    }

    @Test
    void deleteFlightTouristTest() throws InterruptedException {
        ValidatableResponse response = delete(baseURI + "/13/tourists?touristId=200").then().statusCode(302);
        response = delete(baseURI + "/13/tourists?touristId=200").then().statusCode(400);

    }

    @Test
    void postFlightTouristTest() throws InterruptedException {
        ValidatableResponse response = post(baseURI + "/13/tourists?touristId=14").then().statusCode(201);
        response = post(baseURI + "/13/tourists?touristId=14").then().statusCode(409);
    }

    @Test
    void deleteFlightTest() throws InterruptedException {
        ValidatableResponse response = delete(baseURI + "/2").then().statusCode(302);
        response = get(baseURI + "/2").then().statusCode(404);
    }

}
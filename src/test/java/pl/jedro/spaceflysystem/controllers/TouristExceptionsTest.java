package pl.jedro.spaceflysystem.controllers;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.is;

public class TouristExceptionsTest {
    @Test
    void sampleGetTest() {
        get(TouristController.BASE_URL + "/1").then().statusCode(200).assertThat().body("name", is("Saba"));


    }

    @Test
    void deleteTouristFlightTest() throws InterruptedException {
        delete(TouristController.BASE_URL + "/1/flights?flight_id=99").then().statusCode(302);
        Thread.sleep(50);
        delete(TouristController.BASE_URL + "/1/flights?flight_id=99").then().statusCode(400);


    }

    @Test
    void postTouristFlightTest() throws InterruptedException {
        post(TouristController.BASE_URL + "/1/flights?flight_id=19").then().statusCode(201);
        Thread.sleep(50);
        post(TouristController.BASE_URL + "/1/flights?flight_id=19").then().statusCode(409);

    }

    @Test
    void deleteTouristTest() throws InterruptedException {
        delete(TouristController.BASE_URL + "/2").then().statusCode(302);
        Thread.sleep(50);
        get(TouristController.BASE_URL + "/2").then().statusCode(404);
    }
}

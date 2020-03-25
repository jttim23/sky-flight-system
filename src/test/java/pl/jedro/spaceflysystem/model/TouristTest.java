package pl.jedro.spaceflysystem.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TouristTest {

    @Test
    void deleteFlightTest() {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setSeatQuantity(6);
        Tourist tourist = new Tourist();
        tourist.setFlights(Arrays.asList(flight));
        tourist.setId(1L);
        tourist.setName("Jimmy");
        tourist.deleteFlight(1L);
        assertEquals(tourist.getFlights().size(), 0);

    }

    @Test
    void addFlightTest() {
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setSeatQuantity(6);
        Tourist tourist = new Tourist();
        tourist.setFlights(new ArrayList<>());
        tourist.setId(1L);
        tourist.setName("Jimmy");
        tourist.addFlight(flight);
        assertEquals(tourist.getFlights().size(), 1);

    }


}
package pl.jedro.spaceflysystem.model;

import org.junit.jupiter.api.Test;
import pl.jedro.spaceflysystem.exceptions.DeleteRequestInvalidException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightTest {
    @Test
    void deleteTouristTest() throws DeleteRequestInvalidException {
        Tourist tourist = new Tourist();
        tourist.setId(1L);
        tourist.setName("Jimmy");
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setSeatQuantity(6);
        flight.setTourists(Arrays.asList(tourist));
        flight.deleteTourist(1L);
        assertEquals(flight.getTourists().size(), 0);

    }

    @Test
    void addTouristTest() {
        Tourist tourist = new Tourist();
        tourist.setId(1L);
        tourist.setName("Jimmy");
        Flight flight = new Flight();
        flight.setId(1L);
        flight.setSeatQuantity(6);
        flight.setTourists(new ArrayList<>());
        flight.addTourist(tourist);
        assertEquals(flight.getTourists().size(), 1);

    }

}
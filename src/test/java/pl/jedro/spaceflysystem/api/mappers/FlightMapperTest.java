package pl.jedro.spaceflysystem.api.mappers;


import org.junit.jupiter.api.Test;
import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.model.Flight;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlightMapperTest {
    public static final int seats = 6;
    FlightMapper flightMapper = FlightMapper.INSTANCE;


    @Test
    public void flightDTOToFlight() throws Exception {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setSeatQuantity(seats);

        Flight flight = flightMapper.flightDTOToFlight(flightDTO);
        assertEquals(flightDTO.getSeatQuantity(), flight.getSeatQuantity());
    }

    @Test
    public void flightToFlightDTO() throws Exception {
        Flight flight = new Flight();
        flight.setSeatQuantity(seats);
        FlightDTO flightDTO = flightMapper.flightToDTO(flight);
        assertEquals(flight.getSeatQuantity(), flightDTO.getSeatQuantity());
    }

}
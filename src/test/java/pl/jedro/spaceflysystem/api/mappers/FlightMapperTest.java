package pl.jedro.spaceflysystem.api.mappers;


import org.junit.jupiter.api.Test;
import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.model.Flight;


import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        FlightDTO flightDTO = flightMapper.flightToFlightDTO(flight);
        assertEquals(flight.getSeatQuantity(), flightDTO.getSeatQuantity());
    }
    @Test
    public void flightListToFlightDTOList()throws Exception{
        Flight flight1 = new Flight();
        flight1.setSeatQuantity(seats);
        Flight flight2 = new Flight();
        flight2.setSeatQuantity(seats);
        List<Flight> flights = Arrays.asList(flight1,flight2);
        List<FlightDTO> flightDTOS = flightMapper.flightListToFlightDTOList(flights);
        assertEquals(flightDTOS.get(1).getSeatQuantity(),flights.get(1).getSeatQuantity());

    }
    @Test
    public void flightDTOListToFlightList()throws Exception{
        FlightDTO flight1 = new FlightDTO();
        flight1.setSeatQuantity(seats);
        FlightDTO flight2 = new FlightDTO();
        flight2.setSeatQuantity(seats);
        List<FlightDTO> flightDTOS = Arrays.asList(flight1,flight2);
        List<Flight> flights = flightMapper.flightDTOListToFlightList(flightDTOS);
        assertEquals(flights.get(1).getSeatQuantity(),flightDTOS.get(1).getSeatQuantity());

    }
}
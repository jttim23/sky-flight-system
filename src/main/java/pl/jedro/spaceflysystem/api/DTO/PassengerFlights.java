package pl.jedro.spaceflysystem.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class PassengerFlights {
    private final TouristDTO touristDTO;
    private final List<FlightDTO> flightDTOS;

}

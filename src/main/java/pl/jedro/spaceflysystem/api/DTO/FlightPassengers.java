package pl.jedro.spaceflysystem.api.DTO;

import lombok.Data;
import pl.jedro.spaceflysystem.model.Tourist;

import java.util.List;

@Data
public class FlightPassengers {
    private final FlightDTO flightDTO;
    private final List<TouristDTO> touristDTOS;

}

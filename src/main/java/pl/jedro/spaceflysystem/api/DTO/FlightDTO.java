package pl.jedro.spaceflysystem.api.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jedro.spaceflysystem.model.Tourist;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int seatQuantity;
    private List<Tourist> tourists;
    private double ticketPrice;
    private String flightUrl;

}
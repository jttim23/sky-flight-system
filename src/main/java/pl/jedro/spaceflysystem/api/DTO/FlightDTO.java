package pl.jedro.spaceflysystem.api.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTO {
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int seatQuantity;
    private double ticketPrice;
    private String flightUrl;
}
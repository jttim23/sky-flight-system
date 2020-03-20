package pl.jedro.spaceflysystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int seatQuantity;
    private List<Tourist> tourists;
    private double ticketPrice;

}

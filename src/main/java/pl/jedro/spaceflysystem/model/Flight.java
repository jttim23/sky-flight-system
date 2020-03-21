package pl.jedro.spaceflysystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Flight {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int seatQuantity;
    private List<Tourist> tourists;
    private double ticketPrice;

}

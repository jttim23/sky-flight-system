package pl.jedro.spaceflysystem.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NotNull(message = "Departure time required")
    private LocalDateTime departureTime;
    @Column(nullable = false)
    @NotNull(message = "Arrival time required")
    private LocalDateTime arrivalTime;
    @Column(nullable = false)
    @NotNull(message = "Seat quantity required")
    private int seatQuantity;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "flights_tourists",
            joinColumns = {@JoinColumn(name = "flight_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "tourist_id", referencedColumnName = "id")})
    private List<Tourist> tourists;
    @Column(nullable = false)
    @NotNull(message = "Ticket Price required")
    private double ticketPrice;

    public void addTourist(Tourist tourist) {
        tourists.add(tourist);
    }

}

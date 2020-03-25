package pl.jedro.spaceflysystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.jedro.spaceflysystem.exceptions.BadRequestException;
import pl.jedro.spaceflysystem.exceptions.ResourcePresentException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @JsonIgnore
    private List<Tourist> tourists;
    @Column(nullable = false)
    @NotNull(message = "Ticket Price required")
    private double ticketPrice;

    public void addTourist(Tourist tourist) {
        for (Tourist t : tourists) {
            if (t.getId().equals(tourist.getId())) {
                throw new ResourcePresentException("Flight has tourist with id " + tourist.getId() + " already assigned");
            }
        }
        tourists.add(tourist);
    }

    public void deleteTourist(Long id) {

        List<Tourist> returnList = new ArrayList<Tourist>();
        if (tourists.size() == 0) {
            throw new BadRequestException("Flight does not have tourists assigned");
        } else {
            for (Tourist tourist : tourists) {
                if (!tourist.getId().equals(id)) {
                    returnList.add(tourist);
                }
            }
        }
        if (tourists.size() == returnList.size()) {
            throw new BadRequestException("No tourists with id " + id + "  assigned to Flight");
        }

        this.setTourists(returnList);
    }

}

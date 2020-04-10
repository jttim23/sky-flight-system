package pl.jedro.spaceflysystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pl.jedro.spaceflysystem.exceptions.DeleteRequestInvalidException;
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
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "flights")
    @JsonIgnore
    private List<Tourist> tourists;
    @Column(nullable = false)
    @NotNull(message = "Ticket Price required")
    private double ticketPrice;

    /**
     * Add {@code tourist} to the list of tourists assigned to the {@code Flight}.
     * Also checks if this {@code tourist} is already assigned to this {@code Flight}.
     *
     * @param tourist The tourist to be added.
     * @throws ResourcePresentException
     */
    public void addTourist(Tourist tourist) throws ResourcePresentException {
        for (Tourist t : tourists) {
            if (t.getId().equals(tourist.getId())) {
                throw new ResourcePresentException("Flight has tourist with id " + tourist.getId() + " already assigned");
            }
        }
        tourists.add(tourist);
    }

    /**
     * Delete {@code Tourist} from the list of tourists assigned to the {@code Flight} by {@code id}.
     * <p></>Also checks if this {@code Flight} has any {@code Tourist} assigned, and if so, if {@code Tourist} with passed (@code id)
     * is assigned to this {@code Flight}</p>
     *
     * @param id The tourist id to get deleted.
     * @throws DeleteRequestInvalidException
     */
    public void deleteTourist(Long id) throws DeleteRequestInvalidException {

        List<Tourist> returnList = new ArrayList<Tourist>();
        if (tourists.size() == 0) {
            throw new DeleteRequestInvalidException("Flight does not have tourists assigned");
        } else {
            for (Tourist tourist : tourists) {
                if (!tourist.getId().equals(id)) {
                    returnList.add(tourist);
                }
            }
        }
        if (tourists.size() == returnList.size()) {
            throw new DeleteRequestInvalidException("No tourists with id " + id + "  assigned to Flight");
        }

        this.setTourists(returnList);
    }

}

package pl.jedro.spaceflysystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.jedro.spaceflysystem.exceptions.DeleteRequestInvalidException;
import pl.jedro.spaceflysystem.exceptions.ResourcePresentException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class Tourist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Size(min = 2, max = 30)
    private String name;
    @Column(nullable = false)
    @Size(min = 2, max = 30)
    private String lastName;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(nullable = false)
    @Size(min = 2, max = 3)
    private String country;
    private String notes;
    @Column(nullable = false)
    @NotNull(message = "Date of birth required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "flights_tourists",
            joinColumns = {@JoinColumn(name = "tourist_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "flight_id", referencedColumnName = "id")})
    @JsonIgnore
    private List<Flight> flights;

    /**
     * Add {@code flight} to the list of tourists assigned to the {@code Tourist}.
     * Also checks if this {@code flight} is already assigned to this {@code Tourist}.
     *
     * @param flight The flight to be added.
     * @throws ResourcePresentException
     */
    public void addFlight(Flight flight) {
        for (Flight f : flights) {
            if (f.getId().equals(flight.getId())) {
                throw new ResourcePresentException("Tourist has flight with id " + flight.getId() + " already assigned");
            }
        }
        flights.add(flight);
    }

    /**
     * Delete {@code Flight} from the list of flights assigned to the {@code Tourist} by {@code id}.
     * <p></>Also checks if this {@code Tourist} has any {@code Flight} assigned, and if so, if {@code Flight} with passed (@code id)
     * is assigned to this {@code Tourist}</p>
     *
     * @param id The flight id to get deleted.
     * @throws DeleteRequestInvalidException
     */
    public void deleteFlight(Long id) throws DeleteRequestInvalidException {

        List<Flight> returnList = new ArrayList<Flight>();
        if (flights.size() == 0) {
            throw new DeleteRequestInvalidException("Tourist does not have flights assigned");
        } else {
            for (Flight flight : flights) {
                if (!flight.getId().equals(id)) {
                    returnList.add(flight);
                }
            }
        }
        if (flights.size() == returnList.size()) {
            throw new DeleteRequestInvalidException("No flights with id " + id + " assigned to Tourist");
        }
        this.setFlights(returnList);
    }


}


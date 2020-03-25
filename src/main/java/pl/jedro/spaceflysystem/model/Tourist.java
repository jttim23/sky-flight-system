package pl.jedro.spaceflysystem.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
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
    private Gender sex;
    @Column(nullable = false)
    @Size(min = 2, max = 3)
    private String country;
    private String notes;
    @Column(nullable = false)
    @NotNull(message = "Date of birth required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "tourists")
    private List<Flight> flights;

    public void addFlight(Flight flight) {
        flights.add(flight);
    }


}


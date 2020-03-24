package pl.jedro.spaceflysystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Data
@Entity
public class Tourist {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String sex;
    private String country;
    private String notes;
    private LocalDate dateOfBirth;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "tourists")
    private List<Flight> flights;

}

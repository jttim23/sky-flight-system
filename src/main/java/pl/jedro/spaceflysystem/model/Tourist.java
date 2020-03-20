package pl.jedro.spaceflysystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tourist {
    private String name;
    private String lastName;
    private String sex;
    private String country;
    private String notes;
    private LocalDate dateOfBirth;
    private List<Flight> flights;

}

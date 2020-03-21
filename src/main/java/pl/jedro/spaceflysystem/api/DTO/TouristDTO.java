package pl.jedro.spaceflysystem.api.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jedro.spaceflysystem.model.Flight;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TouristDTO {
    private String name;
    private String lastName;
    private String sex;
    private String country;
    private String notes;
    private LocalDate dateOfBirth;
    private List<Flight> flights;
    private String TouristUrl;

}

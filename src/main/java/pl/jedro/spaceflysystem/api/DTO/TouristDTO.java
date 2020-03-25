package pl.jedro.spaceflysystem.api.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TouristDTO {
    private Long id;
    private String name;
    private String lastName;
    private String sex;
    private String country;
    private String notes;
    private LocalDate dateOfBirth;

}

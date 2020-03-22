package pl.jedro.spaceflysystem.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDTOList {
    private List<FlightDTO> flightDTOS;

}

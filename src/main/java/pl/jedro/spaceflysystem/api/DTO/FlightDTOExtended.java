package pl.jedro.spaceflysystem.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.jedro.spaceflysystem.model.Tourist;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTOExtended extends FlightDTO {
    private List<TouristDTO> tourists;

}

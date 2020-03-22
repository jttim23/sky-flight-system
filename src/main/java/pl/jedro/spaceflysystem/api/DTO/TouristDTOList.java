package pl.jedro.spaceflysystem.api.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TouristDTOList {
    private List<TouristDTO> touristDTOS;
}

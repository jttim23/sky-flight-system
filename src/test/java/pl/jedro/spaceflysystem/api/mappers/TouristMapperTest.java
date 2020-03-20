package pl.jedro.spaceflysystem.api.mappers;

import org.junit.jupiter.api.Test;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.model.Tourist;

import static org.junit.jupiter.api.Assertions.*;

class TouristMapperTest {
    TouristMapper touristMapper = TouristMapper.INSTANCE;
    private static final String NAME = "Jimmy";

    @Test
    void touristToTouristDTO() {
        Tourist tourist = new Tourist();
        tourist.setName(NAME);

        TouristDTO touristDTO = touristMapper.touristToTouristDTO(tourist);
        assertEquals(tourist.getName(), touristDTO.getName());
    }

    @Test
    void touristDTOToTourist() {
        TouristDTO touristDTO = new TouristDTO();
        touristDTO.setName(NAME);

        Tourist tourist = touristMapper.touristDTOToTourist(touristDTO);
        assertEquals(touristDTO.getName(), tourist.getName());
    }
}
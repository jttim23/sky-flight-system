package pl.jedro.spaceflysystem.api.mappers;

import org.junit.jupiter.api.Test;
import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.model.Flight;
import pl.jedro.spaceflysystem.model.Tourist;

import java.util.Arrays;
import java.util.List;

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
    @Test
    public void TouristListToTouristDTOList()throws Exception{
        Tourist tourist1 = new Tourist();
        tourist1.setName(NAME);
        Tourist tourist2 = new Tourist();
        tourist2.setName(NAME);
        List<Tourist> tourists = Arrays.asList(tourist1,tourist2);
        List<TouristDTO> touristDTOS = touristMapper.touristListToTouristDTOList(tourists);
        assertEquals(touristDTOS.get(1).getName(),tourists.get(1).getName());

    }
    @Test
    public void TouristDTOListToTouristList()throws Exception{
        TouristDTO tourist1 = new TouristDTO();
        tourist1.setName(NAME);
        TouristDTO tourist2 = new TouristDTO();
        tourist2.setName(NAME);
        List<TouristDTO> touristDTOS = Arrays.asList(tourist1,tourist2);
        List<Tourist> tourists = touristMapper.touristDTOListToTouristList(touristDTOS);
        assertEquals(tourists.get(1).getName(),touristDTOS.get(1).getName());

    }
}
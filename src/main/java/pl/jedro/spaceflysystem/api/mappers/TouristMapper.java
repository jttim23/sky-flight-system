package pl.jedro.spaceflysystem.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.jedro.spaceflysystem.api.DTO.TouristDTO;
import pl.jedro.spaceflysystem.model.Tourist;

import java.util.List;

@Mapper(componentModel="spring")
public interface TouristMapper {
    TouristMapper INSTANCE = Mappers.getMapper(TouristMapper.class);

    TouristDTO touristToTouristDTO(Tourist tourist);

    Tourist touristDTOToTourist(TouristDTO touristDTO);

    List<Tourist> touristDTOListToTouristList (List<TouristDTO> touristDTOS);
    List<TouristDTO> touristListToTouristDTOList (List<Tourist> tourists);
}

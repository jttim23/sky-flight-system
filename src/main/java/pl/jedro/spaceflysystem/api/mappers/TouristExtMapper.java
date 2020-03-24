package pl.jedro.spaceflysystem.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.jedro.spaceflysystem.api.DTO.TouristDTOExtended;
import pl.jedro.spaceflysystem.model.Tourist;

@Mapper(componentModel = "spring")
public interface TouristExtMapper {
    TouristExtMapper INSTANCE = Mappers.getMapper(TouristExtMapper.class);

    Tourist touristDTOToTourist(TouristDTOExtended touristDTO);

    TouristDTOExtended touristToDTO(Tourist tourist);

}

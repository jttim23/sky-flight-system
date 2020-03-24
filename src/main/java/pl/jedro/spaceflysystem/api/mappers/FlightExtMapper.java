package pl.jedro.spaceflysystem.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.jedro.spaceflysystem.api.DTO.FlightDTOExtended;
import pl.jedro.spaceflysystem.model.Flight;

@Mapper(componentModel = "spring")
public interface FlightExtMapper {


    FlightExtMapper INSTANCE = Mappers.getMapper(FlightExtMapper.class);

    Flight flightDTOToFlight(FlightDTOExtended flightDTO);

    FlightDTOExtended flightToDTO(Flight flight);

}


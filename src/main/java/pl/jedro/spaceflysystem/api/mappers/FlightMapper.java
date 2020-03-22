package pl.jedro.spaceflysystem.api.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pl.jedro.spaceflysystem.api.DTO.FlightDTO;
import pl.jedro.spaceflysystem.model.Flight;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    Flight flightDTOToFlight(FlightDTO flightDTO);

    FlightDTO flightToFlightDTO(Flight flight);

    List<Flight> flightDTOListToFlightList(List<FlightDTO> flightDTOS);

    List<FlightDTO> flightListToFlightDTOList(List<Flight> flights);
}

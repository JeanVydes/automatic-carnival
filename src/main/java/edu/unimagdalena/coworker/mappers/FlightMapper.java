package edu.unimagdalena.coworker.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.dto.FlightDTO.CreateFlightDTO;
import edu.unimagdalena.coworker.dto.FlightDTO.FlightResponseDTO;
import edu.unimagdalena.coworker.domain.entities.Flight;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    FlightResponseDTO toResponseDTO(Flight flight);

    @Mapping(target = "id", ignore = true)
    Flight toEntity(CreateFlightDTO flightDTO);
}

package edu.unimagdalena.coworker.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.domain.dto.FlightDTO;
import edu.unimagdalena.coworker.domain.entities.Flight;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    FlightDTO toDTO(Flight flight);

    @Mapping(target = "id", ignore = true)
    Flight toEntity(FlightDTO flightDTO);
}

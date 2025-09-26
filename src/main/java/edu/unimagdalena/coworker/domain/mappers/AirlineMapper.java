package edu.unimagdalena.coworker.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.domain.dto.AirlineDTO;
import edu.unimagdalena.coworker.domain.entities.Airline;

@Mapper(componentModel = "spring")
public interface AirlineMapper {
    AirlineDTO toDTO(Airline airline);

    @Mapping(target = "id", ignore = true)
    Airline toEntity(AirlineDTO airlineDTO);
}

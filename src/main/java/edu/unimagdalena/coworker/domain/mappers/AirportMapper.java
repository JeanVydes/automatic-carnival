package edu.unimagdalena.coworker.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.domain.dto.AirportDTO;
import edu.unimagdalena.coworker.domain.entities.Airport;

@Mapper(componentModel = "spring")
public interface AirportMapper {
    AirportDTO toDTO(Airport airport);

    @Mapping(target = "id", ignore = true)
    Airport toEntity(AirportDTO airportDTO);
}

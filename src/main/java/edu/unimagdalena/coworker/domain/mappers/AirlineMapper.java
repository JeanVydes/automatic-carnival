package edu.unimagdalena.coworker.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.domain.dto.AirlineDTO.AirlineResponseDTO;
import edu.unimagdalena.coworker.domain.dto.AirlineDTO.CreateAirlineDTO;
import edu.unimagdalena.coworker.domain.entities.Airline;

@Mapper(componentModel = "spring")
public interface AirlineMapper {
    AirlineResponseDTO toResponseDTO(Airline airline);

    @Mapping(target = "id", ignore = true)
    Airline toEntity(CreateAirlineDTO airlineDTO);
}

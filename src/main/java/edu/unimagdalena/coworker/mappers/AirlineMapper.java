package edu.unimagdalena.coworker.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.dto.AirlineDTO.AirlineResponseDTO;
import edu.unimagdalena.coworker.dto.AirlineDTO.CreateAirlineDTO;
import edu.unimagdalena.coworker.domain.entities.Airline;

@Mapper(componentModel = "spring")
public interface AirlineMapper {
    AirlineResponseDTO toResponseDTO(Airline airline);

    @Mapping(target = "id", ignore = true)
    Airline toEntity(CreateAirlineDTO airlineDTO);
}

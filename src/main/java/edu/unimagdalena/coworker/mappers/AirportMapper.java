package edu.unimagdalena.coworker.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.dto.AirportDTO.AirportResponseDTO;
import edu.unimagdalena.coworker.dto.AirportDTO.CreateAirportDTO;
import edu.unimagdalena.coworker.domain.entities.Airport;

@Mapper(componentModel = "spring")
public interface AirportMapper {
    AirportResponseDTO toResponseDTO(Airport airport);

    @Mapping(target = "id", ignore = true)
    Airport toEntity(CreateAirportDTO airportDTO);
}

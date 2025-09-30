package edu.unimagdalena.coworker.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.dto.PassengerDTO.CreatePassengerDTO;
import edu.unimagdalena.coworker.dto.PassengerDTO.PassengerResponseDTO;
import edu.unimagdalena.coworker.domain.entities.Passenger;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    PassengerResponseDTO toResponseDTO(Passenger passenger);

    @Mapping(target = "id", ignore = true)
    Passenger toEntity(CreatePassengerDTO passengerDTO);
}

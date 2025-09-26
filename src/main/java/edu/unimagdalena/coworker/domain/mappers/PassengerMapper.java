package edu.unimagdalena.coworker.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.domain.dto.PassengerDTO;
import edu.unimagdalena.coworker.domain.entities.Passenger;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    PassengerDTO toDTO(Passenger passenger);

    @Mapping(target = "id", ignore = true)
    Passenger toEntity(PassengerDTO passengerDTO);
}

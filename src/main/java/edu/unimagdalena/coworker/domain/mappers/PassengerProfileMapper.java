package edu.unimagdalena.coworker.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.domain.dto.PassengerProfileDTO;
import edu.unimagdalena.coworker.domain.entities.PassengerProfile;

@Mapper(componentModel = "spring")
public interface PassengerProfileMapper {
    PassengerProfileDTO toDTO(PassengerProfile passengerProfile);

    @Mapping(target = "id", ignore = true)
    PassengerProfile toEntity(PassengerProfileDTO passengerProfileDTO);
}

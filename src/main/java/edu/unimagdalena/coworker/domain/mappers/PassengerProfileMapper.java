package edu.unimagdalena.coworker.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.domain.dto.PassengerProfileDTO.CreatePassengerProfileDTO;
import edu.unimagdalena.coworker.domain.dto.PassengerProfileDTO.PassengerProfileResponseDTO;
import edu.unimagdalena.coworker.domain.entities.PassengerProfile;

@Mapper(componentModel = "spring")
public interface PassengerProfileMapper {
    PassengerProfileResponseDTO toResponseDTO(PassengerProfile passengerProfile);

    @Mapping(target = "id", ignore = true)
    PassengerProfile toEntity(CreatePassengerProfileDTO passengerProfileDTO);
}

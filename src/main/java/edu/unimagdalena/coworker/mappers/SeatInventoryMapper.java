package edu.unimagdalena.coworker.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.dto.SeatInventoryDTO.CreateSeatInventoryDTO;
import edu.unimagdalena.coworker.dto.SeatInventoryDTO.SeatInventoryResponseDTO;
import edu.unimagdalena.coworker.domain.entities.SeatInventory;

@Mapper(componentModel = "spring")
public interface SeatInventoryMapper {
    SeatInventoryResponseDTO toResponseDTO(SeatInventory seatInventory);

    @Mapping(target = "id", ignore = true)
    SeatInventory toEntity(CreateSeatInventoryDTO seatInventoryDTO);
}

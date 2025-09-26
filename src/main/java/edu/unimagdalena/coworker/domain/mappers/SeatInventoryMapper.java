package edu.unimagdalena.coworker.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.domain.dto.SeatInventoryDTO;
import edu.unimagdalena.coworker.domain.entities.SeatInventory;

@Mapper(componentModel = "spring")
public interface SeatInventoryMapper {
    SeatInventoryDTO toDTO(SeatInventory seatInventory);

    @Mapping(target = "id", ignore = true)
    SeatInventory toEntity(SeatInventoryDTO seatInventoryDTO);
}

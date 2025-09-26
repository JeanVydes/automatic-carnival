package edu.unimagdalena.coworker.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.domain.dto.BookingItemDTO;
import edu.unimagdalena.coworker.domain.entities.BookingItem;

@Mapper(componentModel = "spring")
public interface BookingItemMapper {
    BookingItemDTO toDTO(BookingItem bookingItem);

    @Mapping(target = "id", ignore = true)
    BookingItem toEntity(BookingItemDTO bookingItemDTO);
}

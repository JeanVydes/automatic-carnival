package edu.unimagdalena.coworker.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.domain.dto.BookingItemDTO.BookingItemResponseDTO;
import edu.unimagdalena.coworker.domain.dto.BookingItemDTO.CreateBookingItemDTO;
import edu.unimagdalena.coworker.domain.entities.BookingItem;

@Mapper(componentModel = "spring")
public interface BookingItemMapper {
    BookingItemResponseDTO toResponseDTO(BookingItem bookingItem);

    @Mapping(target = "id", ignore = true)
    BookingItem toEntity(CreateBookingItemDTO bookingItemDTO);
}

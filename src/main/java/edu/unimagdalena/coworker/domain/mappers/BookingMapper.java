package edu.unimagdalena.coworker.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.domain.dto.BookingDTO;
import edu.unimagdalena.coworker.domain.entities.Booking;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDTO toDTO(Booking booking);

    @Mapping(target = "id", ignore = true)
    Booking toEntity(BookingDTO bookingDTO);
}

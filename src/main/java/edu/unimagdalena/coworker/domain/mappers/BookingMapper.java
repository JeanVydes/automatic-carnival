package edu.unimagdalena.coworker.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.domain.dto.BookingDTO.BookingResponseDTO;
import edu.unimagdalena.coworker.domain.dto.BookingDTO.CreateBookingDTO;
import edu.unimagdalena.coworker.domain.entities.Booking;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingResponseDTO toResponseDTO(Booking booking);

    @Mapping(target = "id", ignore = true)
    Booking toEntity(CreateBookingDTO bookingDTO);
}

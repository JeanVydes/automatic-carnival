package edu.unimagdalena.coworker.domain.services;

import java.util.List;

import edu.unimagdalena.coworker.domain.dto.BookingDTO.BookingResponseDTO;
import edu.unimagdalena.coworker.domain.dto.BookingDTO.CreateBookingDTO;
import edu.unimagdalena.coworker.domain.dto.BookingDTO.UpdateBookingDTO;

// Booking
public interface BookingService {
    BookingResponseDTO create(CreateBookingDTO createBookingDTO);
    BookingResponseDTO getById(Long id);
    List<BookingResponseDTO> getAll();
    void delete(Long id);
    BookingResponseDTO update(Long id, UpdateBookingDTO updateBookingDTO);
}

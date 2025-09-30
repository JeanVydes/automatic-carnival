package edu.unimagdalena.coworker.domain.services;

import java.util.List;

import edu.unimagdalena.coworker.domain.dto.BookingItemDTO.BookingItemResponseDTO;
import edu.unimagdalena.coworker.domain.dto.BookingItemDTO.CreateBookingItemDTO;
import edu.unimagdalena.coworker.domain.dto.BookingItemDTO.UpdateBookingItemDTO;

// BookingItem
public interface BookingItemService {
    BookingItemResponseDTO create(CreateBookingItemDTO createBookingItemDTO);
    BookingItemResponseDTO getById(Long id);
    List<BookingItemResponseDTO> getAll();
    void delete(Long id);
    BookingItemResponseDTO update(Long id, UpdateBookingItemDTO updateBookingItemDTO);
}

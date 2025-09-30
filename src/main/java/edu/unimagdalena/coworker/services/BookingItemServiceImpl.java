package edu.unimagdalena.coworker.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.unimagdalena.coworker.dto.BookingItemDTO.BookingItemResponseDTO;
import edu.unimagdalena.coworker.dto.BookingItemDTO.CreateBookingItemDTO;
import edu.unimagdalena.coworker.dto.BookingItemDTO.UpdateBookingItemDTO;
import edu.unimagdalena.coworker.mappers.BookingItemMapper;
import edu.unimagdalena.coworker.domain.repositories.BookingItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// BookingItem
@Service
@RequiredArgsConstructor
@Transactional
public class BookingItemServiceImpl implements BookingItemService {
    private final BookingItemRepository bookingItemRepository;
    private final BookingItemMapper mapper;

    @Override
    public BookingItemResponseDTO create(CreateBookingItemDTO createBookingItemDTO) {
        return mapper.toResponseDTO(bookingItemRepository.save(mapper.toEntity(createBookingItemDTO)));
    }

    @Override
    public BookingItemResponseDTO getById(Long id) {
        return mapper.toResponseDTO(bookingItemRepository.findById(id).orElseThrow());
    }

    @Override
    public List<BookingItemResponseDTO> getAll() {
        return bookingItemRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        bookingItemRepository.deleteById(id);
    }

    @Override
    public BookingItemResponseDTO update(Long id, UpdateBookingItemDTO updateBookingItemDTO) {
        return bookingItemRepository.findById(id)
                .map(bookingItem -> {
                    return mapper.toResponseDTO(bookingItemRepository.save(bookingItem));
                })
                .orElseThrow();
    }
}

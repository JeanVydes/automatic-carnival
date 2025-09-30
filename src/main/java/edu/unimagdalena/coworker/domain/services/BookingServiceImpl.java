package edu.unimagdalena.coworker.domain.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.unimagdalena.coworker.domain.dto.BookingDTO.BookingResponseDTO;
import edu.unimagdalena.coworker.domain.dto.BookingDTO.CreateBookingDTO;
import edu.unimagdalena.coworker.domain.dto.BookingDTO.UpdateBookingDTO;
import edu.unimagdalena.coworker.domain.mappers.BookingMapper;
import edu.unimagdalena.coworker.domain.repositories.BookingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// Booking
@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper mapper;

    @Override
    public BookingResponseDTO create(CreateBookingDTO createBookingDTO) {
        return mapper.toResponseDTO(bookingRepository.save(mapper.toEntity(createBookingDTO)));
    }

    @Override
    public BookingResponseDTO getById(Long id) {
        return mapper.toResponseDTO(bookingRepository.findById(id).orElseThrow());
    }

    @Override
    public List<BookingResponseDTO> getAll() {
        return bookingRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public BookingResponseDTO update(Long id, UpdateBookingDTO updateBookingDTO) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    return mapper.toResponseDTO(bookingRepository.save(booking));
                })
                .orElseThrow();
    }
}

package edu.unimagdalena.coworker.domain.dto;

import java.io.Serializable;

public class BookingItemDTO {
    public record CreateBookingItemDTO(Long bookingId, Long seatId) implements Serializable {}
    public record UpdateBookingItemDTO(Long id, Long bookingId, Long seatId) implements Serializable {}
    public record BookingItemResponseDTO(Long id, Long bookingId, Long seatId) implements Serializable {}
}

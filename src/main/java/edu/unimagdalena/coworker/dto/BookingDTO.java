package edu.unimagdalena.coworker.dto;

import java.io.Serializable;

public class BookingDTO {
    public record CreateBookingDTO(Long flightId, Long passengerId) implements Serializable {}
    public record UpdateBookingDTO(Long id, Long flightId, Long passengerId) implements Serializable {}
    public record BookingResponseDTO(Long id, Long flightId, Long passengerId) implements Serializable {}
}

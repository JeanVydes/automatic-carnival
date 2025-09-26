package edu.unimagdalena.coworker.domain.dto;

import edu.unimagdalena.coworker.domain.enums.Cabin;

public class SeatInventoryDTO {
    public record CreateSeatInventoryDTO(Cabin cabin, Integer totalSeats, Integer availableSeats, Long flightId) {};
    public record UpdateSeatInventoryDTO(Long id, Cabin cabin, Integer totalSeats, Integer availableSeats, Long flightId) {};
    public record SeatInventoryResponseDTO(Long id, Cabin cabin, Integer totalSeats, Integer availableSeats, Long flightId) {};
}
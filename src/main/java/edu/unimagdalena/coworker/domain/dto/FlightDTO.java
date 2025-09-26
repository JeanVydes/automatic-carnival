package edu.unimagdalena.coworker.domain.dto;

import java.io.Serializable;

public class FlightDTO {
    public record CreateFlightDTO(String origin, String destination, String departureTime, String arrivalTime) implements Serializable {}
    public record UpdateFlightDTO(Long id, String origin, String destination, String departureTime, String arrivalTime) implements Serializable {}
    public record FlightResponseDTO(Long id, String origin, String destination, String departureTime, String arrivalTime) implements Serializable {}
}
package edu.unimagdalena.coworker.dto;

import java.io.Serializable;

public class AirportDTO {
    public record CreateAirportDTO(String name, String code) implements Serializable {}
    public record UpdateAirportDTO(String name, String code) implements Serializable {}
    public record AirportResponseDTO(Long id, String name, String code) implements Serializable {}
}

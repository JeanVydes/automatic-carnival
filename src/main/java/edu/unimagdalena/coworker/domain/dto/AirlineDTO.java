package edu.unimagdalena.coworker.domain.dto;

import java.io.Serializable;

public class AirlineDTO {
    public record CreateAirlineDTO(String name, String code) implements Serializable {}
    public record UpdateAirlineDTO(String name, String code) implements Serializable {}
    public record AirlineResponseDTO(Long id, String name, String code) implements Serializable {}
}

package edu.unimagdalena.coworker.domain.dto;

import java.io.Serializable;

public class PassengerDTO {
    public record CreatePassengerDTO(String fullName, String email, Long passengerProfileId) implements Serializable {}
    public record UpdatePassengerDTO(Long id, String fullName, String email, Long passengerProfileId) implements Serializable {}
    public record PassengerResponseDTO(Long id, String fullName, String email, Long passengerProfileId) implements Serializable {}
}

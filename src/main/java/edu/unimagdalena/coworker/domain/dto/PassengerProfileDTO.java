package edu.unimagdalena.coworker.domain.dto;

import java.io.Serializable;

public class PassengerProfileDTO {
    public record CreatePassengerProfileDTO(String phone, String countryCode, Long passengerId) implements Serializable {}
    public record UpdatePassengerProfileDTO(Long id, String phone, String countryCode, Long passengerId) implements Serializable {}
    public record PassengerProfileResponseDTO(Long id, String phone, String countryCode, Long passengerId) implements Serializable {}
}

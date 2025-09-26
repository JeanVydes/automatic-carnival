package edu.unimagdalena.coworker.domain.dto;

public class TagDTO {
    public record CreateTagDTO(String name) {};
    public record UpdateTagDTO(String name) {};
    public record TagResponseDTO(Long id, String name) {};
}
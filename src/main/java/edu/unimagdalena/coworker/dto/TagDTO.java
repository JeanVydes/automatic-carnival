package edu.unimagdalena.coworker.dto;

import java.io.Serializable;

public class TagDTO {
    public record CreateTagDTO(String name) implements Serializable {}
    public record UpdateTagDTO(String name) implements Serializable {}
    public record TagResponseDTO(Long id, String name) implements Serializable {}
}
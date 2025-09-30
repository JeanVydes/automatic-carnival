package edu.unimagdalena.coworker.domain.services;

import java.util.List;

import edu.unimagdalena.coworker.domain.dto.TagDTO.TagResponseDTO;
import edu.unimagdalena.coworker.domain.dto.TagDTO.CreateTagDTO;
import edu.unimagdalena.coworker.domain.dto.TagDTO.UpdateTagDTO;

// Tag
public interface TagService {
    TagResponseDTO create(CreateTagDTO createTagDTO);
    TagResponseDTO getById(Long id);
    List<TagResponseDTO> getAll();
    void delete(Long id);
    TagResponseDTO update(Long id, UpdateTagDTO updateTagDTO);
}

package edu.unimagdalena.coworker.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.unimagdalena.coworker.dto.TagDTO.TagResponseDTO;
import edu.unimagdalena.coworker.dto.TagDTO.CreateTagDTO;
import edu.unimagdalena.coworker.dto.TagDTO.UpdateTagDTO;
import edu.unimagdalena.coworker.mappers.TagMapper;
import edu.unimagdalena.coworker.domain.repositories.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

// Tag
@Service
@RequiredArgsConstructor
@Transactional
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper mapper;

    @Override
    public TagResponseDTO create(CreateTagDTO createTagDTO) {
        return mapper.toResponseDTO(tagRepository.save(mapper.toEntity(createTagDTO)));
    }

    @Override
    public TagResponseDTO getById(Long id) {
        return mapper.toResponseDTO(tagRepository.findById(id).orElseThrow());
    }

    @Override
    public List<TagResponseDTO> getAll() {
        return tagRepository.findAll().stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        tagRepository.deleteById(id);
    }

    @Override
    public TagResponseDTO update(Long id, UpdateTagDTO updateTagDTO) {
        return tagRepository.findById(id)
                .map(tag -> {
                    return mapper.toResponseDTO(tagRepository.save(tag));
                })
                .orElseThrow();
    }
}

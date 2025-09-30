package edu.unimagdalena.coworker.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.domain.dto.TagDTO.CreateTagDTO;
import edu.unimagdalena.coworker.domain.dto.TagDTO.TagResponseDTO;
import edu.unimagdalena.coworker.domain.entities.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagResponseDTO toResponseDTO(Tag tag);

    @Mapping(target = "id", ignore = true)
    Tag toEntity(CreateTagDTO tag);
}

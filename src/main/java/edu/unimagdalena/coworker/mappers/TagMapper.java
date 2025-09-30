package edu.unimagdalena.coworker.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.dto.TagDTO.CreateTagDTO;
import edu.unimagdalena.coworker.dto.TagDTO.TagResponseDTO;
import edu.unimagdalena.coworker.domain.entities.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagResponseDTO toResponseDTO(Tag tag);

    @Mapping(target = "id", ignore = true)
    Tag toEntity(CreateTagDTO tag);
}

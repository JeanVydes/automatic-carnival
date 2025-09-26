package edu.unimagdalena.coworker.domain.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import edu.unimagdalena.coworker.domain.dto.TagDTO;
import edu.unimagdalena.coworker.domain.entities.Tag;


@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDTO toDTO(Tag tag);

    @Mapping(target = "id", ignore = true)
    Tag toEntity(TagDTO tagDTO);
}

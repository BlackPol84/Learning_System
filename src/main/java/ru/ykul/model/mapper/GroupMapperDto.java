package ru.ykul.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ykul.model.Group;
import ru.ykul.model.dto.GroupDto;

@Mapper
public interface GroupMapperDto {

    @Mapping(target = "courseId", source = "course.id")
    @Mapping(target = "courseTitle", source = "course.title")
    GroupDto toDto(Group group);

    @InheritInverseConfiguration
    Group toGroup(GroupDto groupDto);
}

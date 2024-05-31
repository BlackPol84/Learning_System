package ru.ykul.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ykul.model.Student;
import ru.ykul.model.dto.StudentDto;

@Mapper
public interface StudentMapperDto {

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "groupName", source = "group.name")
    StudentDto toDto(Student student);

    @InheritInverseConfiguration
    Student toStudent(StudentDto studentDto);
}

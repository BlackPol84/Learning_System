package ru.ykul.model.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ykul.model.Course;
import ru.ykul.model.dto.CourseDto;

@Mapper
public interface CourseMapperDto {

    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(target = "firstName", source = "teacher.firstName")
    @Mapping(target = "lastName", source = "teacher.lastName")
    CourseDto toDto(Course course);
    @InheritInverseConfiguration
    Course toCourse(CourseDto courseDto);
}

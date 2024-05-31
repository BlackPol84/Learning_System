package ru.ykul.model.mapper;

import org.mapstruct.Mapper;
import ru.ykul.model.Teacher;
import ru.ykul.model.dto.TeacherDto;

@Mapper
public interface TeacherMapperDto {
    TeacherDto toDto(Teacher teacher);
    Teacher toTeacher(TeacherDto teacherDto);
}

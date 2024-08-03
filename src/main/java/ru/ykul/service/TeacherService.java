package ru.ykul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ykul.exception.TeacherNotFoundException;
import ru.ykul.model.Teacher;
import ru.ykul.model.dto.TeacherDto;
import ru.ykul.model.mapper.TeacherMapperDto;
import ru.ykul.repository.CourseRepository;
import ru.ykul.repository.ScheduleRepository;
import ru.ykul.repository.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeacherService {

    private final CourseRepository courseRepository;
    private final ScheduleRepository scheduleRepository;
    private final TeacherRepository teacherRepository;
    private final TeacherMapperDto mapperDto;

    public List<TeacherDto> findAll() {
        return teacherRepository.getAll().stream().
                map(mapperDto::toDto).collect(Collectors.toList());
    }

    @Transactional
    public List<TeacherDto> create(TeacherDto teacherDto) {
        Teacher teacher = mapperDto.toTeacher(teacherDto);
        teacherRepository.create(teacher);

        return teacherRepository.getAll().stream().
                map(mapperDto::toDto).collect(Collectors.toList());
    }

    public TeacherDto find(int id) {
        Teacher teacher = teacherRepository.getById(id).
                orElseThrow(() -> new TeacherNotFoundException(id));

        return mapperDto.toDto(teacher);
    }

    @Transactional
    public TeacherDto update(TeacherDto teacherDto) {
        Teacher updatedTeacher = mapperDto.toTeacher(teacherDto);

        Teacher teacher = teacherRepository.getById(updatedTeacher.getId())
                .orElseThrow(() -> new TeacherNotFoundException(updatedTeacher.getId()));

        teacher.setFirstName(updatedTeacher.getFirstName());
        teacher.setLastName(updatedTeacher.getLastName());
        teacherRepository.update(teacher);

        return mapperDto.toDto(teacher);
    }

    @Transactional
    public void delete(int id) {
        courseRepository.deleteTeacher(id);
        scheduleRepository.deleteTeacher(id);
        Teacher teacher = teacherRepository.getById(id).
                orElseThrow(() -> new TeacherNotFoundException(id));
        teacherRepository.delete(teacher);
    }
}

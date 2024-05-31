package ru.ykul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ykul.exception.CourseNotFoundException;
import ru.ykul.exception.TeacherNotFoundException;
import ru.ykul.model.Course;
import ru.ykul.model.Teacher;
import ru.ykul.model.dto.CourseDto;
import ru.ykul.model.mapper.CourseMapperDto;
import ru.ykul.repository.CourseRepository;
import ru.ykul.repository.GroupRepository;
import ru.ykul.repository.ScheduleRepository;
import ru.ykul.repository.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;
    private final ScheduleRepository scheduleRepository;
    private final TeacherRepository teacherRepository;
    private final CourseMapperDto mapperDto;

    public List<CourseDto> findAll() {
        return courseRepository.getAll().stream().
                map(mapperDto::toDto).collect(Collectors.toList());
    }

    @Transactional
    public List<CourseDto> create(CourseDto courseDto) {
        Course course = mapperDto.toCourse(courseDto);

        Teacher teacher = teacherRepository.getById(course.getTeacher().getId()).
                orElseThrow(() -> new TeacherNotFoundException(course.getTeacher().getId()));
        course.setTeacher(teacher);

        if(!courseRepository.isExists(course)) {
            courseRepository.create(course);
        }

        return courseRepository.getAll().stream().
                map(mapperDto::toDto).collect(Collectors.toList());
    }

    public CourseDto find(int id) {
        Course course = courseRepository.getById(id).
                orElseThrow(() -> new CourseNotFoundException(id));
        return mapperDto.toDto(course);
    }

    public CourseDto update(CourseDto courseDto) {
        Course course = mapperDto.toCourse(courseDto);
        Teacher teacher = teacherRepository.getById(course.getTeacher().getId()).
                orElseThrow(() -> new TeacherNotFoundException(course.getTeacher().getId()));
        course.setTeacher(teacher);
        courseRepository.update(course);

        Course courseUpdate = courseRepository.getById(course.getId()).
                orElseThrow(() -> new CourseNotFoundException(course.getId()));

        return mapperDto.toDto(courseUpdate);
    }

    @Transactional
    public void delete(int id) {
        groupRepository.deleteCourse(id);
        scheduleRepository.deleteCourse(id);
        Course course = courseRepository.getById(id).
                orElseThrow(() -> new CourseNotFoundException(id));
        courseRepository.delete(course);
    }
}

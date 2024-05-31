package ru.ykul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ykul.exception.CourseNotFoundException;
import ru.ykul.exception.GroupNotFoundException;
import ru.ykul.exception.ScheduleNotFoundException;
import ru.ykul.exception.TeacherNotFoundException;
import ru.ykul.model.Course;
import ru.ykul.model.Group;
import ru.ykul.model.Schedule;
import ru.ykul.model.Teacher;
import ru.ykul.model.dto.ScheduleDto;
import ru.ykul.model.mapper.ScheduleMapperDto;
import ru.ykul.repository.CourseRepository;
import ru.ykul.repository.GroupRepository;
import ru.ykul.repository.ScheduleRepository;
import ru.ykul.repository.TeacherRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final ScheduleMapperDto mapperDto;

    public List<ScheduleDto> findAll() {
        return scheduleRepository.getAll().stream().
                map(mapperDto::toDto).collect(Collectors.toList());
    }

    @Transactional
    public List<ScheduleDto> create(ScheduleDto scheduleDto) {
        Schedule schedule = mapperDto.toSchedule(scheduleDto);

        Group group = groupRepository.getById(schedule.getGroup().getId()).
                orElseThrow(() -> new GroupNotFoundException(schedule.getGroup().getId()));
        Course course = courseRepository.getById(schedule.getCourse().getId()).
                orElseThrow(() -> new CourseNotFoundException(schedule.getCourse().getId()));
        Teacher teacher = teacherRepository.getById(schedule.getTeacher().getId()).
                orElseThrow(() -> new TeacherNotFoundException(schedule.getTeacher().getId()));

        validation(group, course, teacher, schedule);

        schedule.setGroup(group);
        schedule.setCourse(course);
        schedule.setTeacher(teacher);

        boolean scheduleExists = scheduleRepository.isExists(schedule);
        boolean scheduleExistsEndDate = scheduleRepository.isExistsEndDate(schedule);
        if(!scheduleExists && !scheduleExistsEndDate) {
            scheduleRepository.create(schedule);
        }

        return scheduleRepository.getAll().stream().
                map(mapperDto::toDto).collect(Collectors.toList());
    }

    public ScheduleDto find(int id) {
        Schedule schedule = scheduleRepository.getById(id).
                orElseThrow(() -> new ScheduleNotFoundException(id));
        return mapperDto.toDto(schedule);
    }

    @Transactional
    public ScheduleDto update(ScheduleDto scheduleDto) {

        Schedule schedule = mapperDto.toSchedule(scheduleDto);

        Group group = groupRepository.getById(schedule.getGroup().getId()).
                orElseThrow(() -> new GroupNotFoundException(schedule.getGroup().getId()));
        Course course = courseRepository.getById(schedule.getCourse().getId()).
                orElseThrow(() -> new CourseNotFoundException(schedule.getCourse().getId()));
        Teacher teacher = teacherRepository.getById(schedule.getTeacher().getId()).
                orElseThrow(() -> new TeacherNotFoundException(schedule.getTeacher().getId()));

        validation(group, course, teacher, schedule);

        schedule.setGroup(group);
        schedule.setCourse(course);
        schedule.setTeacher(teacher);

        boolean scheduleExists = scheduleRepository.isExists(schedule);
        boolean scheduleExistsEndDate = scheduleRepository.isExistsEndDate(schedule);
        if(!scheduleExists && !scheduleExistsEndDate) {
            scheduleRepository.update(schedule);
        }

        Schedule scheduleUpdate = scheduleRepository.getById(schedule.getId()).
                orElseThrow(() -> new ScheduleNotFoundException(schedule.getId()));

        return mapperDto.toDto(scheduleUpdate);
    }

    public void delete(int id) {
        Schedule schedule = scheduleRepository.getById(id).
                orElseThrow(() -> new ScheduleNotFoundException(id));
        scheduleRepository.delete(schedule);
    }

    private void validation(Group group, Course course, Teacher teacher, Schedule schedule) {

        if (!schedule.getStartDate().isBefore(schedule.getEndDate())) {
            throw new IllegalArgumentException("End date must be greater than start date");
        }

        if(!group.getCourse().getId().equals(course.getId()))
            throw new IllegalArgumentException("The selected group is not in this course");

        if(!course.getTeacher().getId().equals(teacher.getId()))
            throw new IllegalArgumentException("The specified teacher does not teach this course");
    }
}

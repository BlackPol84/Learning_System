package ru.ykul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ykul.exception.CourseNotFoundException;
import ru.ykul.exception.GroupNotFoundException;
import ru.ykul.model.Course;
import ru.ykul.model.Group;
import ru.ykul.model.dto.GroupDto;
import ru.ykul.model.mapper.GroupMapperDto;
import ru.ykul.repository.CourseRepository;
import ru.ykul.repository.GroupRepository;
import ru.ykul.repository.ScheduleRepository;
import ru.ykul.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ScheduleRepository scheduleRepository;
    private final GroupMapperDto mapperDto;

    public List<GroupDto> findAll() {
        return groupRepository.getAll().stream().
                map(mapperDto::toDto).collect(Collectors.toList());
    }

    @Transactional
    public List<GroupDto> create(GroupDto groupDto) {
        Group group = mapperDto.toGroup(groupDto);

        Course course = courseRepository.getById(group.getCourse().getId()).
                orElseThrow(() -> new CourseNotFoundException(group.getCourse().getId()));
        group.setCourse(course);

        if(!groupRepository.isExists(group)) {
            groupRepository.create(group);
        }

        return groupRepository.getAll().stream().
                map(mapperDto::toDto).collect(Collectors.toList());
    }

    public GroupDto find(int id) {
        Group group = groupRepository.getById(id).
                orElseThrow(() -> new GroupNotFoundException(id));
        return mapperDto.toDto(group);
    }

    @Transactional
    public GroupDto update(GroupDto groupDto) {
        Group group = mapperDto.toGroup(groupDto);
        Course course = courseRepository.getById(group.getCourse().getId()).
                orElseThrow(() -> new CourseNotFoundException(group.getCourse().getId()));
        group.setCourse(course);
        groupRepository.update(group);

        Group groupUpdate = groupRepository.getById(group.getId()).
                orElseThrow(() -> new GroupNotFoundException(group.getId()));

        return mapperDto.toDto(groupUpdate);
    }

    @Transactional
    public void delete(int id) {
        studentRepository.deleteGroup(id);
        scheduleRepository.deleteGroup(id);
        Group group = groupRepository.getById(id).
                orElseThrow(() -> new GroupNotFoundException(id));
        groupRepository.delete(group);
    }
}


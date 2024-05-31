package ru.ykul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.ykul.exception.GroupNotFoundException;
import ru.ykul.exception.StudentNotFoundException;
import ru.ykul.model.Group;
import ru.ykul.model.Student;
import ru.ykul.model.dto.StudentDto;
import ru.ykul.model.mapper.StudentMapperDto;
import ru.ykul.repository.GroupRepository;
import ru.ykul.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final StudentMapperDto mapperDto;

    public List<StudentDto> findAll() {
        return studentRepository.getAll().stream().
                map(mapperDto::toDto).collect(Collectors.toList());
    }

    @Transactional
    public List<StudentDto> create(StudentDto studentDto) {
        Student student = mapperDto.toStudent(studentDto);

        Group group = groupRepository.getById(student.getGroup().getId()).
                orElseThrow(() -> new GroupNotFoundException(student.getGroup().getId()));
        student.setGroup(group);

        if(!studentRepository.isExists(student)) {
            studentRepository.create(student);
        }

        return studentRepository.getAll().stream().
                map(mapperDto::toDto).collect(Collectors.toList());
    }

    public StudentDto find(int id) {
        Student student = studentRepository.getById(id).
                orElseThrow(() -> new StudentNotFoundException(id));
        return mapperDto.toDto(student);
    }

    @Transactional
    public StudentDto update(StudentDto studentDto) {
        Student student = mapperDto.toStudent(studentDto);

        Group group = groupRepository.getById(student.getGroup().getId()).
                orElseThrow(() -> new GroupNotFoundException(student.getGroup().getId()));;
        student.setGroup(group);
        studentRepository.update(student);

        Student studentUpdate = studentRepository.getById(student.getId()).
                orElseThrow(() -> new StudentNotFoundException(student.getId()));

        return mapperDto.toDto(studentUpdate);
    }

    public void delete(int id) {
        Student student = studentRepository.getById(id).
                orElseThrow(() -> new StudentNotFoundException(id));
        studentRepository.delete(student);
    }
}

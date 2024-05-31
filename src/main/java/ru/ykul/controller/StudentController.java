package ru.ykul.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ykul.model.dto.StudentDto;
import ru.ykul.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @GetMapping()
    List<StudentDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    StudentDto getOne(@PathVariable int id) {
        return service.find(id);
    }

    @PutMapping("/{id}")
    StudentDto update(@Valid @RequestBody StudentDto student) {
        return service.update(student);
    }

    @PostMapping()
    List<StudentDto> create(@Valid @RequestBody StudentDto student) {
        return service.create(student);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        service.delete(id);
    }
}

package ru.ykul.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ykul.model.dto.TeacherDto;
import ru.ykul.service.TeacherService;

import java.util.List;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService service;

    @GetMapping()
    List<TeacherDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    TeacherDto getOne(@PathVariable int id) {
        return service.find(id);
    }

    @PutMapping("/{id}")
    TeacherDto update(@Valid @RequestBody TeacherDto teacher) {
        return service.update(teacher);
    }

    @PostMapping()
    List<TeacherDto> create(@Valid @RequestBody TeacherDto teacher) {
        return service.create(teacher);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        service.delete(id);
    }
}


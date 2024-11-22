package ru.ykul.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ykul.model.dto.CourseDto;
import ru.ykul.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/lms/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @GetMapping()
    List<CourseDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    CourseDto getOne(@PathVariable int id) {
        return service.find(id);
    }

    @PutMapping("/{id}")
    CourseDto update(@Valid @RequestBody CourseDto course) {
        return service.update(course);
    }

    @PostMapping()
    List<CourseDto> create(@Valid @RequestBody CourseDto course) {
        return service.create(course);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        service.delete(id);
    }
}

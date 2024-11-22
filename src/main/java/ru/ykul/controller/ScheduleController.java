package ru.ykul.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ykul.model.dto.ScheduleDto;
import ru.ykul.service.ScheduleService;

import java.util.List;

@RestController
@RequestMapping("/lms/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService service;

    @GetMapping()
    List<ScheduleDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    ScheduleDto getOne(@PathVariable int id) {
        return service.find(id);
    }

    @PutMapping("/{id}")
    ScheduleDto update(@Valid @RequestBody ScheduleDto schedule) {
        return service.update(schedule);
    }

    @PostMapping()
    List<ScheduleDto> create(@Valid @RequestBody ScheduleDto schedule) {
        return service.create(schedule);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        service.delete(id);
    }
}

package ru.ykul.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.ykul.model.dto.GroupDto;
import ru.ykul.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService service;

    @GetMapping()
    List<GroupDto> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    GroupDto getOne(@PathVariable int id) {
        return service.find(id);
    }

    @PutMapping("/{id}")
    GroupDto update(@Valid @RequestBody GroupDto group) {
        return service.update(group);
    }

    @PostMapping()
    List<GroupDto> create(@Valid @RequestBody GroupDto group) {
        return service.create(group);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable int id) {
        service.delete(id);
    }
}

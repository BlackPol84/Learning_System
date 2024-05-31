package ru.ykul.repository;

import ru.ykul.model.Group;
import ru.ykul.model.dto.GroupDto;

import java.util.List;
import java.util.Optional;

public interface GroupRepository {

    List<Group> getAll();
    Optional<Group> getById(int id);
    boolean isExists(Group group);
    void create(Group group);
    void update(Group group);
    void delete(Group group);
    void deleteCourse(int id);
}

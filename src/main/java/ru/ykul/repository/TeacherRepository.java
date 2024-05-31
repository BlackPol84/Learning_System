package ru.ykul.repository;

import ru.ykul.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository {

    List<Teacher> getAll();
    Optional<Teacher> getById(int id);
    void create(Teacher teacher);
    void update(Teacher teacher);
    void delete(Teacher teacher);
}

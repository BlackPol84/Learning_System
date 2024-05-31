package ru.ykul.repository;

import ru.ykul.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    List<Student> getAll();
    Optional<Student> getById(int id);
    boolean isExists(Student student);
    void create(Student student);
    void update(Student student);
    void delete(Student student);
    void deleteGroup(int id);

}

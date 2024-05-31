package ru.ykul.repository;

import ru.ykul.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {

    List<Course> getAll();
    Optional<Course> getById(int id);
    boolean isExists(Course course);
    void create(Course course);
    void update(Course course);
    void delete(Course course);
    void deleteTeacher(int id);
}

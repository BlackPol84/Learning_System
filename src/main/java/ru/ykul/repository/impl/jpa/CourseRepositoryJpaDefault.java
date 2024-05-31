package ru.ykul.repository.impl.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import ru.ykul.model.Course;
import ru.ykul.repository.CourseRepository;
import ru.ykul.repository.jpa.CourseRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.repository.type", havingValue = "false")
public class CourseRepositoryJpaDefault implements CourseRepository {

    private final CourseRepositoryJpa courseJpa;

    public List<Course> getAll() {
        return courseJpa.getAllCourses();
    }

    public Optional<Course> getById(int id) {
        return courseJpa.findById(id);
    }

    public boolean isExists(Course course) {
        return courseJpa.existsByTitleAndTeacher(course.getTitle(), course.getTeacher());
    }

    public void create(Course course) {
            courseJpa.save(course);
    }

    public void update(Course course) {
        courseJpa.save(course);
    }

    public void delete(Course course) {
        courseJpa.delete(course);
    }

    public void deleteTeacher(int id) {
        courseJpa.deleteTeacher(id);
    }
}

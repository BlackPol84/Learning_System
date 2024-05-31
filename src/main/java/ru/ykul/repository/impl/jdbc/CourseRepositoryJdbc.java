package ru.ykul.repository.impl.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ykul.model.Course;
import ru.ykul.repository.CourseRepository;
import ru.ykul.repository.mapper.CoursesMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.repository.type", havingValue = "true")
public class CourseRepositoryJdbc implements CourseRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Course> getAll() {
        return jdbcTemplate.query("SELECT courses.*, teachers.firstname, teachers.lastname " +
                "FROM courses LEFT JOIN " +
                "teachers ON courses.teacher_id = teachers.id", new CoursesMapper());
    }

    public Optional<Course> getById(int id) {
        return jdbcTemplate.query("SELECT courses.*, teachers.id, " +
                                "teachers.firstname, teachers.lastname " +
                                "FROM courses LEFT JOIN " +
                                "teachers ON courses.teacher_id = teachers.id " +
                                "WHERE courses.id = ?",
                        new Object[]{id}, new CoursesMapper()).
                stream().findAny();
    }

    public void create(Course course) {
        jdbcTemplate.update("INSERT INTO courses (title, description, teacher_id) " +
                            "VALUES (?, ?, ?)",
                    course.getTitle(), course.getDescription(), course.getTeacher().getId());
    }

    public void update(Course course) {
        jdbcTemplate.update("UPDATE courses SET title = ?, description = ?, teacher_id = ? " +
                            "WHERE id = ?",
                    course.getTitle(), course.getDescription(),
                    course.getTeacher().getId(), course.getId());
    }

    public boolean isExists(Course course) {

        String sql = "SELECT EXISTS (SELECT 1 FROM courses s WHERE " +
                "s.title = ? AND s.teacher_id = ?)";

        Object[] params = {course.getTitle(), course.getTeacher().getId()};

        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, params, Boolean.class));
    }

    public void delete(Course course) {
        jdbcTemplate.update("DELETE FROM courses WHERE id = ?", course.getId());
    }

    public void deleteTeacher(int id) {
        jdbcTemplate.update("UPDATE courses SET teacher_id = null WHERE teacher_id = ?", id);
    }
}

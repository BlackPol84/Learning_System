package ru.ykul.repository.impl.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ykul.repository.mapper.StudentsMapper;
import ru.ykul.model.Student;
import ru.ykul.repository.StudentRepository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.repository.type", havingValue = "true")
public class StudentRepositoryJdbc implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Student> getAll() {
        return jdbcTemplate.query("SELECT students.*, groups.name " +
                "FROM students LEFT JOIN " +
                "groups ON students.group_id = groups.id", new StudentsMapper());
    }

    public Optional<Student> getById(int id) {
        return jdbcTemplate.query("SELECT students.*, groups.id, groups.name " +
                                "FROM students LEFT JOIN " +
                                "groups ON students.group_id = groups.id " +
                                "WHERE students.id = ?",
                        new Object[]{id}, new StudentsMapper()).stream().
                findAny();
    }

    public void create(Student student) {
        jdbcTemplate.update("INSERT INTO students (firstName, lastName, group_id) " +
                            "VALUES (?, ?, ?)",
                    student.getFirstName(), student.getLastName(), student.getGroup().getId());
    }

    public void update(Student student) {
        jdbcTemplate.update("UPDATE students SET firstName = ?, lastName = ?, group_id = ? " +
                "WHERE id = ?", student.getFirstName(), student.getLastName(),
                student.getGroup().getId(), student.getId());
    }

    public boolean isExists(Student student) {

        String sql = "SELECT EXISTS (SELECT 1 FROM students s WHERE " +
                "s.firstName = ? AND s.lastName = ? AND " +
                "s.group_id = ?)";

        Object[] params = {student.getFirstName(), student.getLastName(), student.getGroup().getId()};

        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, params, Boolean.class));
    }

    public void delete(Student student) {
        jdbcTemplate.update("DELETE FROM students WHERE id = ?", student.getId());
    }

    public void deleteGroup(int id) {
        jdbcTemplate.update("UPDATE students SET group_id = null WHERE group_id = ?", id);
    }
}

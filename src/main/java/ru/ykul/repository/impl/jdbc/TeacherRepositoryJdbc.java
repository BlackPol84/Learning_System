package ru.ykul.repository.impl.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ykul.model.Teacher;
import ru.ykul.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.repository.type", havingValue = "true")
public class TeacherRepositoryJdbc implements TeacherRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Teacher> getAll() {
        return jdbcTemplate.query("SELECT * FROM teachers",
                new BeanPropertyRowMapper<>(Teacher.class));
    }

    public Optional<Teacher> getById(int id) {
        return jdbcTemplate.query("SELECT teachers.id, teachers.firstname, teachers.lastname " +
                                "FROM teachers WHERE id = ?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Teacher.class)).
                stream().findAny();
    }

    public void create(Teacher teacher) {
        jdbcTemplate.update("INSERT INTO teachers (firstname, lastname) VALUES (?, ?)",
                teacher.getFirstName(), teacher.getLastName());
    }

    public void update(Teacher teacher) {
        jdbcTemplate.update("UPDATE teachers SET firstname = ?, lastname = ? WHERE id = ?",
                teacher.getFirstName(), teacher.getLastName(), teacher.getId());
    }

    public void delete(Teacher teacher) {
        jdbcTemplate.update("DELETE FROM teachers WHERE id = ?", teacher.getId());
    }
}

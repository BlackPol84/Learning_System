package ru.ykul.repository.impl.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ykul.model.Group;
import ru.ykul.repository.GroupRepository;
import ru.ykul.repository.mapper.GroupsMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.repository.type", havingValue = "true")
public class GroupRepositoryJdbc implements GroupRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Group> getAll() {
        return jdbcTemplate.query("SELECT groups.*, courses.title " +
                "FROM groups LEFT JOIN " +
                "courses ON groups.course_id = courses.id", new GroupsMapper());
    }

    public Optional<Group> getById(int id) {
        return jdbcTemplate.query("SELECT groups.*, courses.id, courses.title " +
                                "FROM groups LEFT JOIN " +
                                "courses ON groups.course_id = courses.id " +
                                "WHERE groups.id = ?",
                        new Object[]{id}, new GroupsMapper()).
                stream().findAny();
    }

    public void create(Group group) {
        jdbcTemplate.update("INSERT INTO groups (name, course_id) " +
                            "VALUES (?, ?)",
                    group.getName(), group.getCourse().getId());
    }

    public void update(Group group) {
        jdbcTemplate.update("UPDATE groups SET name = ?, course_id = ? " +
                        "WHERE id = ?", group.getName(), group.getCourse().getId(),
                group.getId());
    }

    public boolean isExists(Group group) {

        String sql = "SELECT EXISTS (SELECT 1 FROM groups g WHERE " +
                "g.name = ? AND g.course_id = ?)";

        Object[] params = {group.getName(), group.getCourse().getId()};

        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, params, Boolean.class));
    }

    public void delete(Group group) {
        jdbcTemplate.update("DELETE FROM groups WHERE id = ?", group.getId());
    }

    public void deleteCourse(int id) {
        jdbcTemplate.update("UPDATE groups SET course_id = null WHERE course_id = ?", id);
    }
}

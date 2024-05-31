package ru.ykul.repository.impl.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ykul.model.Schedule;
import ru.ykul.repository.ScheduleRepository;
import ru.ykul.repository.mapper.ScheduleMapper;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.repository.type", havingValue = "true")
public class ScheduleRepositoryJdbc implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<Schedule> getAll() {
        return jdbcTemplate.query("SELECT schedule.*, groups.name, teachers.firstname, " +
                "teachers.lastname, courses.title " +
                "FROM schedule " +
                "LEFT JOIN groups ON schedule.group_id = groups.id " +
                "LEFT JOIN teachers ON schedule.teacher_id = teachers.id " +
                "LEFT JOIN courses ON schedule.course_id = courses.id", new ScheduleMapper());
    }

    public Optional<Schedule> getById(int id) {
        return jdbcTemplate.query("SELECT schedule.*, groups.id, groups.name, " +
                                "teachers.id, teachers.firstname, teachers.lastname, " +
                                "courses.id, courses.title " +
                                "FROM schedule " +
                                "LEFT JOIN groups ON schedule.group_id = groups.id " +
                                "LEFT JOIN teachers ON schedule.teacher_id = teachers.id " +
                                "LEFT JOIN courses ON schedule.course_id = courses.id " +
                                "WHERE schedule.id = ?",
                        new Object[]{id}, new ScheduleMapper()).
                stream().findAny();
    }

    public void update(Schedule schedule) {
        jdbcTemplate.update("UPDATE schedule SET group_id = ?, " +
                        "teacher_id = ?, course_id = ?, " +
                        "start_date = ?, end_date = ? " +
                        "WHERE id = ?",
                schedule.getGroup().getId(), schedule.getTeacher().getId(),
                schedule.getCourse().getId(), schedule.getStartDate(),
                schedule.getEndDate(), schedule.getId());
    }

    public void create(Schedule schedule) {
        jdbcTemplate.update("INSERT INTO schedule (group_id, teacher_id, " +
                        "course_id, start_date, end_date) " +
                        "VALUES(?, ?, ?, ?, ?)",
                schedule.getGroup().getId(), schedule.getTeacher().getId(),
                schedule.getCourse().getId(),
                schedule.getStartDate(), schedule.getEndDate());
    }

    public boolean isExists(Schedule schedule) {

        String sql = "SELECT EXISTS (SELECT 1 FROM schedule s WHERE " +
                "s.group_id = ? AND s.teacher_id = ? AND " +
                "s.course_id = ? AND s.start_date = ? AND " +
                "s.end_date = ?)";

        Object[] params = {schedule.getGroup().getId(), schedule.getTeacher().getId(),
                schedule.getCourse().getId(), schedule.getStartDate(), schedule.getEndDate()};

        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, params, Boolean.class));
    }

    public boolean isExistsEndDate(Schedule schedule) {

        String sql = "SELECT EXISTS (SELECT 1 FROM schedule s WHERE " +
                "s.teacher_id = ? AND s.end_date > ?)";

        Object[] params = {schedule.getTeacher().getId(), schedule.getStartDate()};

        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, params, Boolean.class));
    }

    public void delete(Schedule schedule) {
        jdbcTemplate.update("DELETE FROM schedule WHERE id = ?", schedule.getId());
    }

    public void deleteTeacher(int id) {
        jdbcTemplate.update("UPDATE schedule SET teacher_id = null " +
                "WHERE teacher_id = ?", id);
    }

    public void deleteGroup(int id) {
        jdbcTemplate.update("UPDATE schedule SET group_id = null " +
                "WHERE group_id = ?", id);
    }

    public void deleteCourse(int id) {
        jdbcTemplate.update("UPDATE schedule SET course_id = null " +
                "WHERE course_id = ?", id);
    }
}
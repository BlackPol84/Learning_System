package ru.ykul.repository.impl.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import ru.ykul.model.Schedule;
import ru.ykul.repository.ScheduleRepository;
import ru.ykul.repository.jpa.ScheduleRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.repository.type", havingValue = "false")
public class ScheduleRepositoryJpaDefault implements ScheduleRepository {

    private final ScheduleRepositoryJpa scheduleJpa;

    public List<Schedule> getAll() {
        return scheduleJpa.getAllSchedules();
    }

    public Optional<Schedule> getById(int id) {
        return scheduleJpa.findById(id);
    }

    public void create(Schedule schedule) {
        scheduleJpa.save(schedule);
    }

    public boolean isExists(Schedule schedule) {
        return scheduleJpa.isExists(schedule);
    }

    public boolean isExistsEndDate(Schedule schedule) {
        return scheduleJpa.isExistsEndDate(schedule);
    }

    public void update(Schedule schedule) {
        scheduleJpa.save(schedule);
    }

    public void delete(Schedule schedule) {
        scheduleJpa.delete(schedule);
    }

    public void deleteTeacher(int id) {
        scheduleJpa.deleteTeacherId(id);
    }

    public void deleteGroup(int id) {
        scheduleJpa.deleteGroupId(id);
    }

    public void deleteCourse(int id) {
        scheduleJpa.deleteCourseId(id);
    }
}

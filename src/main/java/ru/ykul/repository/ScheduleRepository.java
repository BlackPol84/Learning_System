package ru.ykul.repository;

import ru.ykul.model.Schedule;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {

    List<Schedule> getAll();
    Optional<Schedule> getById(int id);
    void create(Schedule schedule);
    boolean isExists(Schedule schedule);
    boolean isExistsEndDate(Schedule schedule);
    void update(Schedule schedule);
    void delete(Schedule schedule);
    void deleteTeacher(int id);
    void deleteGroup(int id);
    void deleteCourse(int id);

}

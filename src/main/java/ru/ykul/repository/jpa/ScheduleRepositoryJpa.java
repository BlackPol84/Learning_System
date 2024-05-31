package ru.ykul.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ykul.model.Schedule;

import java.util.List;

public interface ScheduleRepositoryJpa extends JpaRepository<Schedule,Integer> {


    @Query("SELECT s FROM Schedule s " +
            "LEFT JOIN FETCH s.group " +
            "LEFT JOIN FETCH s.teacher " +
            "LEFT JOIN FETCH s.course")
    List<Schedule> getAllSchedules();

    @Query(value = "SELECT EXISTS(SELECT 1 FROM schedule WHERE " +
            "group_id = :#{#schedule.group.id} AND " +
            "teacher_id = :#{#schedule.teacher.id} AND " +
            "course_id = :#{#schedule.course.id} AND " +
            "start_date = :#{#schedule.startDate} AND " +
            "end_date = :#{#schedule.endDate})", nativeQuery = true)
    boolean isExists(@Param("schedule") Schedule schedule);


    @Query(value = "SELECT EXISTS(SELECT 1 FROM schedule WHERE " +
            "teacher_id = :#{#schedule.teacher.id} AND " +
            "end_date > :#{#schedule.startDate})", nativeQuery = true)
    boolean isExistsEndDate(@Param("schedule") Schedule schedule);

    @Modifying
    @Query(value = "UPDATE schedule SET group_id = null " +
            "WHERE group_id = :group_id", nativeQuery = true)
    void deleteGroupId(@Param("group_id") Integer id);

    @Modifying
    @Query(value = "UPDATE schedule SET teacher_id = null " +
            "WHERE teacher_id = :teacher_id", nativeQuery = true)
    void deleteTeacherId(@Param("teacher_id") Integer id);

    @Modifying
    @Query(value = "UPDATE schedule SET course_id = null " +
            "WHERE course_id = :course_id", nativeQuery = true)
    void deleteCourseId(@Param("course_id") Integer id);
}

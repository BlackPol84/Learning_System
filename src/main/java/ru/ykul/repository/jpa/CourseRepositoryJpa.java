package ru.ykul.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ykul.model.Course;
import ru.ykul.model.Teacher;

import java.util.List;

public interface CourseRepositoryJpa extends JpaRepository<Course,Integer> {

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.teacher")
    List<Course> getAllCourses();

    boolean existsByTitleAndTeacher(String title, Teacher teacher);

    @Modifying
    @Query(value = "UPDATE courses SET teacher_id = null " +
            "WHERE teacher_id = :teacher_id", nativeQuery = true)
    void deleteTeacher(@Param("teacher_id") Integer id);
}

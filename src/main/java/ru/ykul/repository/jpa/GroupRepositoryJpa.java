package ru.ykul.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ykul.model.Group;

import java.util.List;

public interface GroupRepositoryJpa extends JpaRepository<Group,Integer> {

    @Query("SELECT g FROM Group g LEFT JOIN FETCH g.course c LEFT JOIN FETCH c.teacher")
    List<Group> getAllGroups();

    boolean existsByName(String name);

    @Modifying
    @Query(value = "UPDATE groups SET course_id = null " +
            "WHERE course_id = :course_id", nativeQuery = true)
    void deleteCourseId(@Param("course_id") Integer id);
}

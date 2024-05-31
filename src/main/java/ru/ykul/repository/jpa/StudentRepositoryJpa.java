package ru.ykul.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.ykul.model.Student;

import java.util.List;

public interface StudentRepositoryJpa extends JpaRepository<Student,Integer> {

    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.group g " +
            "LEFT JOIN FETCH g.course c LEFT JOIN FETCH c.teacher")
    List<Student> getAllStudents();

    boolean existsByFirstNameAndLastNameAndGroupName(String firstName,
                                                     String lastName,
                                                     String name);

    @Modifying
    @Query(value = "UPDATE students SET group_id = null " +
            "WHERE group_id = :group_id", nativeQuery = true)
    void deleteGroupId(@Param("group_id") Integer id);
}

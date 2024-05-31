package ru.ykul.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ykul.model.Teacher;

public interface TeacherRepositoryJpa extends JpaRepository<Teacher,Integer> {

}

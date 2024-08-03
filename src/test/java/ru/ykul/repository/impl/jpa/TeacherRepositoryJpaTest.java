package ru.ykul.repository.impl.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ykul.model.Teacher;
import ru.ykul.repository.impl.AbstractIntegrationTestInitializer;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TeacherRepositoryJpaTest extends AbstractIntegrationTestInitializer {

    @Autowired
    private TeacherRepositoryJpaDefault teacherRepository;

    @Test
    void createTeacher_returnNewTeacher() {

        Teacher teacher = new Teacher();
        teacher.setFirstName("Severus");
        teacher.setLastName("Snape");

        teacherRepository.create(teacher);

        List<Teacher> teachers = teacherRepository.getAll();

        assertEquals(1, teachers.size());

        Optional<Teacher> teacherOptional = teachers.stream().findFirst();

        assertTrue(teacherOptional.isPresent(), "The teacher has null values");
        assertEquals("Severus", teacherOptional.get().getFirstName());
        assertEquals("Snape", teacherOptional.get().getLastName());
    }

    @Test
    void getByIdTeacher_ifExist_returnTeacher() {

        Teacher teacher = new Teacher();
        teacher.setFirstName("Severus");
        teacher.setLastName("Snape");

        teacherRepository.create(teacher);

        List<Teacher> teachers = teacherRepository.getAll();
        Optional<Teacher> teacherOptional = teachers.stream().findFirst();
        assertTrue(teacherOptional.isPresent(), "The teacher has null values");

        int id = teacherOptional.get().getId();

        teacherOptional = teacherRepository.getById(id);

        assertTrue(teacherOptional.isPresent(), "The teacher has null values");
        Teacher teacherResult = teacherOptional.get();
        assertEquals(id, teacherResult.getId());
        assertEquals("Severus", teacherResult.getFirstName());
        assertEquals("Snape", teacherResult.getLastName());
    }

    @Test
    void getAll_ifExistTeachers_returnListTeachers() {

        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Severus");
        teacher1.setLastName("Snape");

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Lord");
        teacher2.setLastName("Voldemort");

        Teacher teacher3 = new Teacher();
        teacher3.setFirstName("Dolores");
        teacher3.setLastName("Umbridge");

        teacherRepository.create(teacher1);
        teacherRepository.create(teacher2);
        teacherRepository.create(teacher3);

        List<Teacher> teachers = teacherRepository.getAll();

        assertEquals(3, teachers.size());
        assertNotNull(teachers.get(0), "A teacher with an index of 0 has null values");
        assertNotNull(teachers.get(1), "A teacher with an index of 1 has null values");
        assertNotNull(teachers.get(2), "A teacher with an index of 2 has null values");

        assertTrue(teachers.stream().anyMatch(t -> t.getFirstName().equals("Severus") &&
                t.getLastName().equals("Snape")));
        assertTrue(teachers.stream().anyMatch(t -> t.getFirstName().equals("Lord") &&
                t.getLastName().equals("Voldemort")));
        assertTrue(teachers.stream().anyMatch(t -> t.getFirstName().equals("Dolores") &&
                t.getLastName().equals("Umbridge")));

        Set<Integer> ids = teachers.stream().mapToInt(Teacher::getId).boxed()
                .collect(Collectors.toSet());

        assertEquals(3, ids.size());
    }

    @Test
    void getAll_ifTableIsEmpty_returnEmptyList() {

        List<Teacher> teachers = teacherRepository.getAll();
        assertEquals(0, teachers.size());
    }

    @Test
    void updateTeacher_ifExist_returnUpdatedTeacher() {

        Teacher teacher = new Teacher();
        teacher.setFirstName("Severus");
        teacher.setLastName("Snape");

        teacherRepository.create(teacher);

        List<Teacher> teachers = teacherRepository.getAll();

        int id = teachers.get(0).getId();

        Teacher updatedTeacher = new Teacher();
        updatedTeacher.setId(id);
        updatedTeacher.setFirstName("Minerva");
        updatedTeacher.setLastName("McGonagall");

        teacherRepository.update(updatedTeacher);

        List<Teacher> updatedTeachers = teacherRepository.getAll();

        assertEquals(1, updatedTeachers.size());

        Optional<Teacher> updatedTeacherOptional = updatedTeachers.stream().findFirst();

        assertTrue(updatedTeacherOptional.isPresent(), "The teacher has null values");

        assertEquals(id, updatedTeacherOptional.get().getId());
        assertEquals("Minerva", updatedTeacherOptional.get().getFirstName());
        assertEquals("McGonagall", updatedTeacherOptional.get().getLastName());
    }

    @Test
    void updateTeacher_ifNotExist_returnNewTeacher() {

        Teacher teacher = new Teacher();
        teacher.setId(1);
        teacher.setFirstName("Minerva");
        teacher.setLastName("McGonagall");

        teacherRepository.update(teacher);

        List<Teacher> teachers = teacherRepository.getAll();

        assertEquals(1, teachers.size());

        Optional<Teacher> teacherOptional = teachers.stream().findFirst();

        assertTrue(teacherOptional.isPresent(), "The teacher has null values");

        assertEquals("Minerva", teacherOptional.get().getFirstName());
        assertEquals("McGonagall", teacherOptional.get().getLastName());
    }

    @Test
    void deleteTeacher_returnEmptyList() {

        Teacher teacher = new Teacher();
        teacher.setFirstName("Severus");
        teacher.setLastName("Snape");

        teacherRepository.create(teacher);

        List<Teacher> teachers = teacherRepository.getAll();

        assertEquals("Severus", teachers.get(0).getFirstName());
        assertEquals("Snape", teachers.get(0).getLastName());

        teacherRepository.delete(teachers.get(0));

        List<Teacher> result = teacherRepository.getAll();
        assertEquals(0, result.size());
    }
}

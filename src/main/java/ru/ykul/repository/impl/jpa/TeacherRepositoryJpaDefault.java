package ru.ykul.repository.impl.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import ru.ykul.model.Teacher;
import ru.ykul.repository.TeacherRepository;
import ru.ykul.repository.jpa.TeacherRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.repository.type", havingValue = "false")
public class TeacherRepositoryJpaDefault implements TeacherRepository {

    private final TeacherRepositoryJpa teacherJpa;

    @Override
    public List<Teacher> getAll() {
        return teacherJpa.findAll();
    }

    @Override
    public Optional<Teacher> getById(int id) {
        return teacherJpa.findById(id);
    }

    @Override
    public void create(Teacher teacher) {
            teacherJpa.save(teacher);
    }

    @Override
    public void update(Teacher teacher) {
        teacherJpa.save(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
        teacherJpa.delete(teacher);
    }
}

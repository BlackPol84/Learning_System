package ru.ykul.repository.impl.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import ru.ykul.model.Student;
import ru.ykul.repository.jpa.StudentRepositoryJpa;
import ru.ykul.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.repository.type", havingValue = "false")
public class StudentRepositoryJpaDefault implements StudentRepository {

    private final StudentRepositoryJpa studentJpa;

    public List<Student> getAll() {
        return studentJpa.getAllStudents();
    }

    public Optional<Student> getById(int id) {
        return studentJpa.findById(id);
    }

    public boolean isExists(Student student) {
        return studentJpa.existsByFirstNameAndLastNameAndGroupName(student.getFirstName(),
                student.getLastName(), student.getGroup().getName());
    }

    public void create(Student student) {
        studentJpa.save(student);
    }

    public void update(Student student) {
        studentJpa.save(student);
    }

    public void delete(Student student) {
        studentJpa.delete(student);
    }

    public void deleteGroup(int id) {
        studentJpa.deleteGroupId(id);
    }
}

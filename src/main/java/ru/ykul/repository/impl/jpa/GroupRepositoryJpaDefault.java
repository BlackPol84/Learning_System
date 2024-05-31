package ru.ykul.repository.impl.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import ru.ykul.model.Group;
import ru.ykul.repository.GroupRepository;
import ru.ykul.repository.jpa.GroupRepositoryJpa;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.repository.type", havingValue = "false")
public class GroupRepositoryJpaDefault implements GroupRepository {

    private final GroupRepositoryJpa groupJpa;

    public List<Group> getAll() {
        return groupJpa.getAllGroups();
    }

    public Optional<Group> getById(int id) {
        return groupJpa.findById(id);
    }

    public boolean isExists(Group group) {
        return groupJpa.existsByName(group.getName());
    }

    public void create(Group group) {
        groupJpa.save(group);
    }

    public void update(Group group) {
        groupJpa.save(group);
    }

    public void delete(Group group) {
        groupJpa.delete(group);
    }

    public void deleteCourse(int id) {
        groupJpa.deleteCourseId(id);
    }
}

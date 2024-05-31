package ru.ykul.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ykul.model.Group;
import ru.ykul.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentsMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setFirstName(rs.getString("firstname"));
        student.setLastName(rs.getString("lastname"));

        Integer groupId = (Integer) rs.getObject("group_id");

        if(groupId != null) {

            Group group = new Group();
            group.setId(rs.getInt("group_id"));
            group.setName(rs.getString("name"));

            student.setGroup(group);
        }

        return student;
    }
}

package ru.ykul.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.ykul.model.Course;
import ru.ykul.model.Group;
import ru.ykul.model.Schedule;
import ru.ykul.model.Teacher;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleMapper implements RowMapper<Schedule> {
    @Override
    public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {

        Schedule schedule = new Schedule();
        schedule.setId(rs.getInt("id"));
        schedule.setStartDate(rs.getTimestamp("start_date").
                toLocalDateTime());
        schedule.setEndDate(rs.getTimestamp("end_date").
                toLocalDateTime());

        Integer groupId = (Integer) rs.getObject("group_id");

        if(groupId != null) {

            Group group = new Group();
            group.setId(rs.getInt("group_id"));
            group.setName(rs.getString("name"));
            schedule.setGroup(group);
        }

        Integer teacherId = (Integer) rs.getObject("teacher_id");

        if(teacherId != null) {

            Teacher teacher = new Teacher();
            teacher.setId(rs.getInt("teacher_id"));
            teacher.setFirstName(rs.getString("firstname"));
            teacher.setLastName(rs.getString("lastname"));
            teacher.setEmail(rs.getString("email"));
            schedule.setTeacher(teacher);
        }

        Integer courseId = (Integer) rs.getObject("course_id");

        if(courseId != null) {

            Course course = new Course();
            course.setId(rs.getInt("course_id"));
            course.setTitle(rs.getString("title"));
            schedule.setCourse(course);
        }

        return schedule;
    }
}

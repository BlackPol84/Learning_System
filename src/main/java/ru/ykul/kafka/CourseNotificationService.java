package ru.ykul.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ykul.model.MessageType;
import ru.ykul.model.Schedule;
import ru.ykul.model.Student;
import ru.ykul.model.dto.CourseNotificationDto;
import ru.ykul.repository.jpa.ScheduleRepositoryJpa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CourseNotificationService {

    private final ScheduleRepositoryJpa repositoryJpa;

    public List<CourseNotificationDto> getCourseStartNextWeek() {

        LocalDateTime intervalDateTime = LocalDateTime.now().plusWeeks(1);
        List<CourseNotificationDto> eventDtoList = new ArrayList<>();

        List<Schedule> schedules = repositoryJpa.findScheduleByStartDate(intervalDateTime);

        for(Schedule schedule : schedules) {
            eventDtoList.add(createTeacherNotification(schedule));
            eventDtoList.addAll(createStudentNotification(schedule));
        }

        return eventDtoList;
    }

    private CourseNotificationDto createTeacherNotification(Schedule schedule) {

        String message = String.format("Dear %s %s, the %s course will start on %tD",
                schedule.getTeacher().getFirstName(),
                schedule.getTeacher().getLastName(),
                schedule.getCourse().getTitle(),
                schedule.getStartDate());

        return new CourseNotificationDto
                (MessageType.EMAIL, schedule.getTeacher().getEmail(), message);
    }

    private List<CourseNotificationDto> createStudentNotification(Schedule schedule) {

        List<CourseNotificationDto> notificationDtos = new ArrayList<>();
        String course = schedule.getCourse().getTitle();
        String message;

        for(Student student : schedule.getGroup().getStudents()) {
            message = String.format("Dear %s %s, the %s course will start on %tD",
                    student.getFirstName(),
                    student.getLastName(),
                    course,
                    schedule.getStartDate());

            notificationDtos.add(new CourseNotificationDto
                    (MessageType.EMAIL, student.getEmail(), message));
        }
        return notificationDtos;
    }
}

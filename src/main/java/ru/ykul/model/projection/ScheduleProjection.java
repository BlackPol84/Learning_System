package ru.ykul.model.projection;

import java.time.LocalDateTime;

public interface ScheduleProjection {
    String getCourseTitle();
    LocalDateTime getScheduleStartDate();
    String getStudentFirstName();
    String getStudentLastName();
    String getStudentEmail();
    String getTeacherFirstName();
    String getTeacherLastName();
    String getTeacherEmail();
}

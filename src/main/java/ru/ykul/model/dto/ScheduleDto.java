package ru.ykul.model.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ScheduleDto {

    private Integer id;

    @NotNull(message = "Group ID should not be null")
    private Integer groupId;

    @NotBlank(message = "Group should not be empty")
    private String groupName;

    @NotNull(message = "Teacher ID should not be null")
    private Integer teacherId;

    @NotBlank(message = "First name should not be empty")
    private String firstName;

    @NotBlank(message = "Last name should not be empty")
    private String lastName;

    @NotNull(message = "Course ID should not be null")
    private Integer courseId;

    @NotBlank(message = "Course should not be empty")
    private String courseTitle;

    @NotNull(message = "Start date should not be null")
    @FutureOrPresent(message = "The date must be in the present or future")
    private LocalDateTime startDate;

    @NotNull(message = "End date should not be null")
    @Future(message = "The date must be in the future")
    private LocalDateTime endDate;
}

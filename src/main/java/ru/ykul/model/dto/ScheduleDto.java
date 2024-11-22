package ru.ykul.model.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ScheduleDto(Integer id,
                          @NotNull(message = "Group ID should not be null")
                          Integer groupId,
                          @NotBlank(message = "Group should not be empty")
                          String groupName,
                          @NotNull(message = "Teacher ID should not be null")
                          Integer teacherId,
                          @NotBlank(message = "First name should not be empty")
                          String firstName,
                          @NotBlank(message = "Last name should not be empty")
                          String lastName,
                          @NotNull(message = "Course ID should not be null")
                          Integer courseId,
                          @NotBlank(message = "Course should not be empty")
                          String courseTitle,
                          @NotNull(message = "Start date should not be null")
                          @FutureOrPresent(message = "The date must be in the present or future")
                          LocalDateTime startDate,
                          @NotNull(message = "End date should not be null")
                          @Future(message = "The date must be in the future")
                          LocalDateTime endDate) { }

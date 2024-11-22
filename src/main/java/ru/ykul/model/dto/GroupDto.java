package ru.ykul.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record GroupDto(Integer id,
                       @NotBlank(message = "Name should not be empty")
                       @Size(min = 5, max = 20, message = "Name should be between 2 and 30 characters")
                       String name,
                       @NotNull(message = "Course ID should not be null")
                       Integer courseId,
                       @NotBlank(message = "Course should not be empty")
                       String courseTitle) { }

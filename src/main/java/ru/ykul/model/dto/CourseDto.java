package ru.ykul.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CourseDto(Integer id,
                        @NotBlank(message = "Title should not be empty")
                        @Size(min = 2, max = 40, message = "Title should be between 2 and 30 characters")
                        String title,
                        @NotBlank(message = "Description should not be empty")
                        @Size(min = 40, max = 500, message = "Title should be between 40 and 500 characters")
                        String description,
                        @NotNull(message = "Teacher ID should not be null")
                        Integer teacherId,
                        @NotBlank(message = "First name should not be empty")
                        String firstName,
                        @NotBlank(message = "Last name should not be empty")
                        String lastName) { }

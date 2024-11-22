package ru.ykul.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TeacherDto(Integer id,
                         @NotBlank(message = "First name should not be empty")
                         @Size(min = 2, max = 30, message = "First name should be between 2 and 30 characters")
                         String firstName,
                         @NotBlank(message = "Last name should not be empty")
                         @Size(min = 2, max = 30, message = "Last name should be between 2 and 30 characters")
                         String lastName,
                         @NotBlank(message = "Email cannot be empty")
                         String email) { }

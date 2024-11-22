package ru.ykul.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StudentDto(Integer id,
                         @NotBlank(message = "Name should not be empty")
                         @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
                         String firstName,
                         @NotBlank(message = "Surname should not be empty")
                         @Size(min = 2, max = 30, message = "Surname should be between 2 and 30 characters")
                         String lastName,
                         @NotBlank(message = "Email cannot be empty")
                         String email,
                         @NotNull(message = "Group ID should not be null")
                         Integer groupId,
                         @NotBlank(message = "Group should not be empty")
                         String groupName) { }

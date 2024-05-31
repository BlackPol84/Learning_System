package ru.ykul.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GroupDto {

    private Integer id;

    @NotBlank(message = "Name should not be empty")
    @Size(min = 5, max = 20, message = "Name should be between 2 and 30 characters")
    private String name;

    @NotNull(message = "Course ID should not be null")
    private Integer courseId;

    @NotBlank(message = "Course should not be empty")
    private String courseTitle;
}

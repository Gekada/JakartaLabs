package com.example.lab5.Student.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentDto {

    @NotNull(message = "Student ID cannot be null")
    private long id;

    @Size(min = 2, max = 50, message = "Student name must be between 2 and 50 characters")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    private Long groupId;
}
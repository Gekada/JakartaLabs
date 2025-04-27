package com.example.lab5.Group.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {

    @NotNull(message = "Group ID cannot be null")
    private long id;

    @NotNull(message = "Group name cannot be null")
    @Size(min = 2, max = 50, message = "Group name must be between 2 and 50 characters")
    private String name;
}
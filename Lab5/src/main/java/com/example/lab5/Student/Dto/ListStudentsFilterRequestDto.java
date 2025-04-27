package com.example.lab5.Student.Dto;

import com.example.lab5.Common.Dto.PaginatedRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListStudentsFilterRequestDto extends PaginatedRequest {
    private String name;

    private String groupId;

    private String email;
}
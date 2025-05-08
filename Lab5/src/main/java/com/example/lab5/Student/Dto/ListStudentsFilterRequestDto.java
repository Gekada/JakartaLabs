package com.example.lab5.Student.Dto;

import com.example.lab5.Common.Dto.PaginatedRequest;
import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListStudentsFilterRequestDto extends PaginatedRequest {
    @QueryParam("name")
    private String name;

    @QueryParam("groupName")
    private String groupName;

    @QueryParam("email")
    private String email;
}
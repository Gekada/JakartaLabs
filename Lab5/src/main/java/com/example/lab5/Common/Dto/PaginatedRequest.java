package com.example.lab5.Common.Dto;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.QueryParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedRequest {
    @QueryParam("page")
    @DefaultValue("1")
    private int page;

    @QueryParam("size")
    @DefaultValue("10")
    private int size;
}
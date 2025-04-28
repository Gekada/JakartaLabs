package com.example.lab5.Common.Dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginatedResponse<T> {
    private List<T> data;
    private int totalItems;
    private int totalPages;
    private int currentPage;

    public PaginatedResponse(List<T> data, int totalItems, int currentPage, int pageSize) {
        this.data = data;
        this.totalItems = totalItems;
        this.currentPage = currentPage;
        this.totalPages = (int) Math.ceil((double) totalItems / pageSize);
    }
}
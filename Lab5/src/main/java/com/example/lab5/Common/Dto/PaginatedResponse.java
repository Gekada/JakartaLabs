package com.example.lab5.Common.Dto;

import java.util.List;

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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
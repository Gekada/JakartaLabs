package com.example.lab5.Common.Dto;

public class PaginatedRequest {
    private int page;
    private int size;

    public PaginatedRequest() {
        this.page = 1;
        this.size = 10;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
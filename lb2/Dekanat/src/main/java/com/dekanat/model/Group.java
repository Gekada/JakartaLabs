package com.dekanat.model;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private Integer id;
    private String name;
    private List<User> students;

    public Group(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.students = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

}

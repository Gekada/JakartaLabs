package org.example.lab_3.model;

import java.io.Serializable;

public class Student implements Serializable {
    private String surname;
    private String groupName;

    public Student(String surname, String groupName) {
        this.surname = surname;
        this.groupName = groupName;
    }

    public String getSurname() {
        return surname;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setGroup(String groupName) {
        this.groupName = groupName;
    }
}

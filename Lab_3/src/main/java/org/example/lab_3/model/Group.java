package org.example.lab_3.model;

import java.io.Serializable;

public class Group implements Serializable {
    private String name;
    private String description;

    public Group() {}

    public Group(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

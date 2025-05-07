package com.example.lb4.Common.Data.Entity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;


@Getter
@Entity
@Table(name = "group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> students;
}
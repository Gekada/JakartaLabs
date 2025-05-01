package com.example.lb4.Common.Data.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Setter
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

}

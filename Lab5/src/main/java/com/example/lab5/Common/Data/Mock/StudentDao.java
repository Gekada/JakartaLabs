package com.example.lab5.Common.Data.Mock;

import com.example.lab5.Common.Data.Entity.Student;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class StudentDao {

    private static final Map<Long, Student> STUDENTS = new HashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong(100);

    static {
        STUDENTS.put(101L, new Student(101L, "Jane Doe", "jane.doe@example.com", 1L));
        STUDENTS.put(102L, new Student(102L, "John Smith", "john.smith@example.com", 2L));
    }

    public Student create(Student student) {
        long id = ID_GENERATOR.incrementAndGet();
        student.setId(id);
        STUDENTS.put(id, student);
        return student;
    }

    public Optional<Student> getById(Long studentId) {
        return Optional.ofNullable(STUDENTS.get(studentId));
    }

    public List<Student> getAll() {
        return new ArrayList<>(STUDENTS.values());
    }

    public Student update(Student student) {
        if (!STUDENTS.containsKey(student.getId())) {
            throw new NoSuchElementException("Student with ID " + student.getId() + " not found.");
        }
        STUDENTS.put(student.getId(), student);
        return student;
    }

    public boolean deleteById(Long studentId) {
        if (!STUDENTS.containsKey(studentId)) {
            throw new NoSuchElementException("Student with ID " + studentId + " not found.");
        }
        STUDENTS.remove(studentId);

        return true;
    }
}

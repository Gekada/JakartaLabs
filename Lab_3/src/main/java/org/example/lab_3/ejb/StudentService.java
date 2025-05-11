package org.example.lab_3.ejb;

import org.example.lab_3.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    List<Student> search(String surname, String groupName);

    void updateStudent(Student student);

    void addStudent(Student student);

    void deleteStudent(String surname);
}

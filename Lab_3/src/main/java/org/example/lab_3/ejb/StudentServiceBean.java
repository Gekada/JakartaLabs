package org.example.lab_3.ejb;

import jakarta.ejb.Stateless;
import org.example.lab_3.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class StudentServiceBean implements StudentService {
    private final List<Student> students = new ArrayList<>(Arrays.asList(
            new Student("Кайданюк", "ІМ-41мн"),
            new Student("Ільницький", "ІК-41мп"),
            new Student("Нікітченко", "ІМ-41мн"),
            new Student("Стельмашенко", "ІТ-41мн")
    ));

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public void updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getSurname().equalsIgnoreCase(updatedStudent.getSurname())) {
                students.set(i, updatedStudent);
                break;
            }
        }
    }

    @Override
    public List<Student> search(String surname, String groupName) {
        return students.stream()
                .filter(s -> (surname == null || surname.isBlank() ||
                        s.getSurname().toLowerCase().contains(surname.toLowerCase()))
                        &&
                        (groupName == null || groupName.isBlank() ||
                                s.getGroupName().equalsIgnoreCase(groupName)))
                .collect(Collectors.toList());
    }

    @Override
    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public void deleteStudent(String surname) {
        students.removeIf(s -> s.getSurname().equalsIgnoreCase(surname));
    }
}

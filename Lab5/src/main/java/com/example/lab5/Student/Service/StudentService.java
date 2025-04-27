package com.example.lab5.Student.Service;

import com.example.lab5.Common.Data.Entity.Student;
import com.example.lab5.Common.Data.Mock.GroupDao;
import com.example.lab5.Common.Data.Mock.StudentDao;
import com.example.lab5.Common.Dto.PaginatedResponse;
import com.example.lab5.Student.Dto.CreateStudentDto;
import com.example.lab5.Student.Dto.ListStudentsFilterRequestDto;
import com.example.lab5.Student.Dto.StudentDto;
import com.example.lab5.Student.Dto.UpdateStudentDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

public class StudentService {

    private final StudentDao studentDAO = new StudentDao();
    private final GroupDao groupDAO = new GroupDao();

    public PaginatedResponse<StudentDto> listStudents(ListStudentsFilterRequestDto filterRequestDto) {
        List<Student> students = studentDAO.getAll();

        if (filterRequestDto != null) {
            students = students.stream()
                    .filter(student -> filterRequestDto.getName() == null || student.getName().toLowerCase().contains(filterRequestDto.getName().toLowerCase()))
                    .filter(student -> filterRequestDto.getEmail() == null || student.getEmail().toLowerCase().contains(filterRequestDto.getEmail().toLowerCase()))
                    .filter(student -> filterRequestDto.getGroupId() == null || Objects.equals(student.getGroupId(), filterRequestDto.getGroupId()))
                    .collect(Collectors.toList());
        }

        int page = filterRequestDto.getPage();
        int pageSize = filterRequestDto.getSize();

        int totalItems = students.size();
        int skip = (page - 1) * pageSize;

        List<Student> paginatedStudents = students.stream()
                .skip(skip)
                .limit(pageSize)
                .collect(Collectors.toList());

        List<StudentDto> mappedStudents = paginatedStudents.stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());

        return new PaginatedResponse<StudentDto>(
                mappedStudents,
                totalItems,
                page,
                pageSize
        );
    }

    public StudentDto getStudentById(Long id) {
        Student student = studentDAO.getById(id)
                .orElseThrow(() -> new NoSuchElementException("Student with ID " + id + " not found."));

        return mapToResponseDTO(student);
    }

    public StudentDto createStudent(CreateStudentDto createStudentDto) {
        if (!groupDAO.existsById(createStudentDto.getGroupId())) {
            throw new IllegalArgumentException("Group with ID " + createStudentDto.getGroupId() + " does not exist.");
        }

        Student student = new Student(0, createStudentDto.getName(),
                createStudentDto.getEmail(), createStudentDto.getGroupId());
        Student createdStudent = studentDAO.create(student);

        return mapToResponseDTO(createdStudent);
    }

    public StudentDto updateStudent(UpdateStudentDto updateStudentDto) {
        Student existingStudent = studentDAO.getById(updateStudentDto.getId())
                .orElseThrow(() -> new NoSuchElementException("Student with ID " + updateStudentDto.getId() + " not found."));

        if (updateStudentDto.getName() != null) {
            existingStudent.setName(updateStudentDto.getName());
        }

        if (updateStudentDto.getEmail() != null) {
            existingStudent.setEmail(updateStudentDto.getEmail());
        }

        if (updateStudentDto.getGroupId() != null) {
            if (!groupDAO.existsById(updateStudentDto.getGroupId())) {
                throw new IllegalArgumentException("Group with ID " + updateStudentDto.getGroupId() + " does not exist.");
            }

            existingStudent.setGroupId(updateStudentDto.getGroupId());
        }

        Student updatedStudent = studentDAO.update(existingStudent);

        return mapToResponseDTO(updatedStudent);
    }

    public boolean deleteStudent(Long id) {
        return studentDAO.deleteById(id);
    }

    private StudentDto mapToResponseDTO(Student student) {
        String groupName = groupDAO.getById(student.getGroupId())
                .map(group -> group.getName())
                .orElse("Unknown Group");

        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                groupName
        );
    }
}
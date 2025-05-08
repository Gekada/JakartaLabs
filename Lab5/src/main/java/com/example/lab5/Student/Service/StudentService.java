package com.example.lab5.Student.Service;

import com.example.lb4.Common.Data.Dto.PaginatedResponse;
import com.example.lab5.Student.Dto.CreateStudentDto;
import com.example.lab5.Student.Dto.ListStudentsFilterRequestDto;
import com.example.lab5.Student.Dto.StudentDto;
import com.example.lab5.Student.Dto.UpdateStudentDto;
import com.example.lb4.Common.Data.Dao.GroupDao;
import com.example.lb4.Common.Data.Dao.StudentDao;
import com.example.lb4.Common.Data.Entity.Group;
import com.example.lb4.Common.Data.Entity.Student;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class StudentService {

    private final StudentDao studentDAO;
    private final GroupDao groupDAO;

    @Inject
    StudentService(StudentDao studentDAO, GroupDao groupDAO) {
        this.studentDAO = studentDAO;
        this.groupDAO = groupDAO;
    }

    public PaginatedResponse<StudentDto> listStudents(ListStudentsFilterRequestDto filterRequestDto) {
        PaginatedResponse<Student> paginatedStudents = studentDAO.getPageFiltered(
                filterRequestDto.getName(),
                filterRequestDto.getEmail(),
                filterRequestDto.getGroupName(),
                filterRequestDto.getPage(),
                filterRequestDto.getSize()
        );

        List<StudentDto> studentDtos = paginatedStudents.getData()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();

        return new PaginatedResponse<>(
                studentDtos,
                paginatedStudents.getTotalItems(),
                paginatedStudents.getCurrentPage(),
                filterRequestDto.getSize()
        );
    }

    public StudentDto getStudentById(Long id) {
        Student student = studentDAO.getById(id);

        return mapToResponseDTO(student);
    }

    public StudentDto createStudent(CreateStudentDto createStudentDto) {
        Group group = groupDAO.getById(createStudentDto.getGroupId());

        Student student = new Student();
        student.setName(createStudentDto.getName());
        student.setEmail(createStudentDto.getEmail());
        student.setGroup(group);
        Student createdStudent = studentDAO.create(student);

        return mapToResponseDTO(createdStudent);
    }

    public StudentDto updateStudent(UpdateStudentDto updateStudentDto) {
        Student existingStudent = studentDAO.getById(updateStudentDto.getId());

        if (updateStudentDto.getName() != null) {
            existingStudent.setName(updateStudentDto.getName());
        }

        if (updateStudentDto.getEmail() != null) {
            existingStudent.setEmail(updateStudentDto.getEmail());
        }

        if (updateStudentDto.getGroupId() != null) {
            Group group = groupDAO.getById(updateStudentDto.getGroupId());

            existingStudent.setGroup(group);
        }

        studentDAO.update(existingStudent);

        return mapToResponseDTO(existingStudent);
    }

    public boolean deleteStudent(Long id) {
        return studentDAO.deleteById(id);
    }

    private StudentDto mapToResponseDTO(Student student) {
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getGroup().getName()
        );
    }
}
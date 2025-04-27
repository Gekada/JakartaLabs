package com.example.lab5.Student.Controller;

import com.example.lab5.Common.Dto.PaginatedResponse;
import com.example.lab5.Student.Dto.CreateStudentDto;
import com.example.lab5.Student.Dto.ListStudentsFilterRequestDto;
import com.example.lab5.Student.Dto.StudentDto;
import com.example.lab5.Student.Dto.UpdateStudentDto;
import com.example.lab5.Student.Service.StudentService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.NoSuchElementException;

@Path("/students")
@Produces("application/json")
@Consumes("application/json")
public class StudentController {

    private final StudentService studentService = new StudentService();

    @GET
    public Response listStudents(@BeanParam ListStudentsFilterRequestDto filter) {
        PaginatedResponse<StudentDto> students = studentService.listStudents(filter);

        return Response.ok(students).build();
    }

    @GET
    @Path("/{id}")
    public Response getStudent(@PathParam("id") Long id) {
        try {
            StudentDto studentResponseDTO = studentService.getStudentById(id);
            return Response.ok(studentResponseDTO).build();
        } catch (NoSuchElementException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response createStudent(@Valid CreateStudentDto createStudentDto) {
        studentService.createStudent(createStudentDto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateStudent(@PathParam("id") Long id, @Valid CreateStudentDto body) {
        var updateStudentDto = new UpdateStudentDto(id, body.getName(), body.getEmail(), body.getGroupId());
        StudentDto updatedStudent = studentService.updateStudent(updateStudentDto);
        if (updatedStudent == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Student not found").build();
        }

        return Response.ok(updatedStudent).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") Long id) {
        if (!studentService.deleteStudent(id)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Student not found").build();
        }

        return Response.noContent().build();
    }
}
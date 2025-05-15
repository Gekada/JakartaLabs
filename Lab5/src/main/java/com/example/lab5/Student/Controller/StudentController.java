package com.example.lab5.Student.Controller;

import com.example.lab5.Student.Dto.CreateStudentDto;
import com.example.lab5.Student.Dto.ListStudentsFilterRequestDto;
import com.example.lab5.Student.Dto.StudentDto;
import com.example.lab5.Student.Dto.UpdateStudentDto;
import com.example.lab5.Student.Service.StudentService;
import com.example.lb4.Common.Data.Dto.PaginatedResponse;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentController {

    @Inject
    private StudentService studentService;

    @GET
    public Response listStudents(@BeanParam ListStudentsFilterRequestDto filter) {
        PaginatedResponse<StudentDto> students = studentService.listStudents(filter);
        return Response.ok(students).build();
    }

    @GET
    @Path("/{id}")
    public Response getStudent(@PathParam("id") int id) {
        StudentDto studentResponseDTO = studentService.getStudentById(id);
        return Response.ok(studentResponseDTO).build();
    }

    @POST
    public Response createStudent(@Valid CreateStudentDto createStudentDto) {
        StudentDto student = studentService.createStudent(createStudentDto);
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateStudent(@PathParam("id") int id, @Valid CreateStudentDto body) {
        var updateStudentDto = new UpdateStudentDto(id, body.getName(), body.getEmail(), body.getGroupId());
        StudentDto updatedStudent = studentService.updateStudent(updateStudentDto);
        if (updatedStudent == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Student not found").build();
        }

        return Response.ok(updatedStudent).build();
    }

    @PUT
    @Path("/{studentId}/transfer/{newGroupId}")
    public Response transferStudentToGroup(@PathParam("studentId") int studentId,
                                           @PathParam("newGroupId") int newGroupId) {
        studentService.transferStudentToGroup(studentId, newGroupId);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") int id) {
        studentService.deleteStudent(id);
        return Response.noContent().build();
    }
}
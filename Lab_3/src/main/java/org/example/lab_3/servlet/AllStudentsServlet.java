package org.example.lab_3.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.lab_3.ejb.StudentService;
import org.example.lab_3.model.Student;

import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class AllStudentsServlet extends HttpServlet {

    @EJB
    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Student> students = studentService.getAllStudents();
        req.setAttribute("students", students);
        req.getRequestDispatcher("/students.jsp").forward(req, resp);
    }
}

package org.example.lab_3.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.lab_3.ejb.StudentService;

import java.io.IOException;

@WebServlet("/delete-student")
public class DeleteStudentServlet extends HttpServlet {

    @EJB
    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8"); // ОБОВ'ЯЗКОВО
        String surname = req.getParameter("surname");

        if (surname != null && !surname.isBlank()) {
            studentService.deleteStudent(surname);
        }

        resp.sendRedirect("students");
    }
}

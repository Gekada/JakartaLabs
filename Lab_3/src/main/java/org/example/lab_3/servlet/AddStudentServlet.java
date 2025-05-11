package org.example.lab_3.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.lab_3.ejb.StudentService;
import org.example.lab_3.model.Student;

import java.io.IOException;

@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {

    @EJB
    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/add_student.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String surname = req.getParameter("surname");
        String group = req.getParameter("group");

        if (surname != null && group != null) {
            studentService.addStudent(new Student(surname, group));
        }

        resp.sendRedirect("students");
    }
}

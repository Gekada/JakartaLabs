package org.example.lab_3.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.lab_3.ejb.StudentService;
import org.example.lab_3.model.Student;

import java.io.IOException;
import java.util.List;

@WebServlet("/edit-student")
public class EditStudentServlet extends HttpServlet {

    @EJB
    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String surname = req.getParameter("surname");

        // Знаходимо студента
        Student student = studentService.getAllStudents().stream()
                .filter(s -> s.getSurname().equalsIgnoreCase(surname))
                .findFirst()
                .orElse(null);

        req.setAttribute("student", student);
        req.getRequestDispatcher("/edit_student.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String originalSurname = req.getParameter("originalSurname"); // ← старе
        String surname = req.getParameter("surname");
        String group = req.getParameter("group");

        // Видаляємо старого + додаємо нового
        studentService.deleteStudent(originalSurname);
        studentService.addStudent(new Student(surname, group));

        resp.sendRedirect("students");
    }
}

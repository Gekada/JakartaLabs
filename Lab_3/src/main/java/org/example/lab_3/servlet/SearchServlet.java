package org.example.lab_3.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.example.lab_3.ejb.StudentService;
import org.example.lab_3.model.Student;

import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    @EJB
    private StudentService studentService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String surname = req.getParameter("surname");
        String groupName = req.getParameter("groupName");

        List<Student> results = studentService.search(surname, groupName);
        req.setAttribute("results", results);

        System.out.println("Пошук: " + surname + " / " + groupName);
        System.out.println("Результати знайдено: " + results.size());

        req.getRequestDispatcher("/results.jsp").forward(req, resp);
    }
}

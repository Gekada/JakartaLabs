package org.example.lab_3.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.lab_3.ejb.GroupService;
import org.example.lab_3.model.Group;

import java.io.IOException;

@WebServlet("/add-group")
public class AddGroupServlet extends HttpServlet {

    @EJB
    private GroupService groupService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/add_group.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        if (name != null && !name.isBlank()) {
            groupService.addGroup(new Group(name, description));
        }

        resp.sendRedirect(req.getContextPath() + "/groups");
    }
}

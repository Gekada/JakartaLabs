package org.example.lab_3.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.lab_3.ejb.GroupService;
import org.example.lab_3.model.Group;

import java.io.IOException;

@WebServlet("/edit-group")
public class EditGroupServlet extends HttpServlet {

    @EJB
    private GroupService groupService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");

        Group group = groupService.getAllGroups().stream()
                .filter(g -> g.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        req.setAttribute("group", group);
        req.getRequestDispatcher("/edit_group.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String description = req.getParameter("description");

        groupService.updateGroup(new Group(name, description));
        resp.sendRedirect("groups");
    }
}

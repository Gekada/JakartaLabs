package org.example.lab_3.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.lab_3.ejb.GroupService;
import org.example.lab_3.model.Group;

import java.io.IOException;
import java.util.List;

@WebServlet("/groups")
public class GroupServlet extends HttpServlet {

    @EJB
    private GroupService groupService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Group> groups = groupService.getAllGroups();
        req.setAttribute("groups", groups);
        req.getRequestDispatcher("/groups.jsp").forward(req, resp);
    }
}

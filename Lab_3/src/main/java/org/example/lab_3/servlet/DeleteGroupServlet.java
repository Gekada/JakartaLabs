package org.example.lab_3.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.lab_3.ejb.GroupService;

import java.io.IOException;

@WebServlet("/delete-group")
public class DeleteGroupServlet extends HttpServlet {

    @EJB
    private GroupService groupService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String groupName = req.getParameter("name");
        if (groupName != null && !groupName.isBlank()) {
            groupService.deleteGroup(groupName);
        }

        resp.sendRedirect("groups");
    }
}

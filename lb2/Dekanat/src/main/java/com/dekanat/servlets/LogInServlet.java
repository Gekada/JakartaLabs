package com.dekanat.servlets;

import com.dekanat.model.User;
import com.dekanat.services.*;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInServlet extends HttpServlet {

 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ServletContext context = getServletContext();
        IUserService userService = (IUserService) context.getAttribute("userServ");
        IGroupService groupService = (IGroupService) context.getAttribute("groupServ");

        String log = request.getParameter("log");
        User user = userService.getByLogin(log);

        if (user == null) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        String pass = request.getParameter("pas");

        if (!userService.checkPassword(user, pass)) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }
        request.getSession().setAttribute("groups", groupService.getAll());
        request.getSession().setAttribute("user", user);
        request.getRequestDispatcher("main.jsp").forward(request, response);
    
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

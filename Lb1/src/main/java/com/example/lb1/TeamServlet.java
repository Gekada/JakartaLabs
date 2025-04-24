package com.example.lb1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/team")
public class TeamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>Сторінка бригади</title></head><body>");
        out.println("<h1>Сторінка сервлета</h1>");
        out.println("<p><a href=\"index.html\">Index</a></p>");
        out.println("</body></html>");
    }
}
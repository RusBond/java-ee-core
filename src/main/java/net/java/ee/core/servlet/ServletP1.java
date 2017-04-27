package net.java.ee.core.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ServletP1")
public class ServletP1 extends HttpServlet {

    boolean isClock = true;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        ServletContext servletContext = request.getServletContext();
        HttpSession session = request.getSession();
        if(isClock) {
            servletContext.setAttribute("servletContext", "servletContext");
            session.setAttribute("session", "session");
            request.setAttribute("request", "request");
            isClock = false;
        }
        try {
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();
            printWriter.println("<h2>");
            printWriter.println("servletContext = " + servletContext.getAttribute("servletContext"));
            printWriter.println("</br>");
            printWriter.println("session = " + session.getAttribute("session"));
            printWriter.println("</br>");
            printWriter.println("request = " + request.getAttribute("request"));
            printWriter.println("<h2>");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
}
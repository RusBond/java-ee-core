package net.java.ee.core.servlet.createServletProgrammno;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProgrammaticallyConfiguredServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.println("Это сообщение было сгенерировано из сервлета, который был сконфигурирован программным путем.");
    }
}
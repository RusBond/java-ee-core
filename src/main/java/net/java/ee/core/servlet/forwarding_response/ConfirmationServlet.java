package net.java.ee.core.servlet.forwarding_response;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ConfirmationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            PrintWriter printWriter;
            List<String> checkedLabels = (List<String>) request.getAttribute("checkedLabels");
            response.setContentType("text/html");
            printWriter = response.getWriter();
            printWriter.println("<p>");
            printWriter.print("Was presented with the following options:");
            printWriter.println("<br/>");
            if (checkedLabels != null) {
                for (String optionLabel : checkedLabels) {
                    printWriter.print(optionLabel);
                    printWriter.println("<br/>");
                }
            } else {
                printWriter.println("Any option chosen was not.");
            }
            printWriter.println("</p>");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
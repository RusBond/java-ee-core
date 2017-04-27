package net.java.ee.core.servlet.example;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletContext_exam", urlPatterns = {"/ServletContext_exam"})
public class ServletContext_exam extends HttpServlet {


    private void init(HttpServletRequest request, HttpServletResponse response){
        try {
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();
            printWriter.println("getQueryString() = " + request.getContextPath());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        init(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException{
        init(request, response);
    }

}

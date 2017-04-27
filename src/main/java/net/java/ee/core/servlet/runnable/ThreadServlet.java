package net.java.ee.core.servlet.runnable;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ThreadServlet", urlPatterns = {"/ThreadServlet"})
public class ThreadServlet extends HttpServlet {
    int i;
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        synchronized (this) {
            try {
                Thread.currentThread().sleep(5000);
                out.print("<h1>Stoping " + i + "</h1>");
            }
            catch (InterruptedException e) {
                System.err.println("Interrupted");
            }
        }
        out.print("<h1>Finished " + i++ + "</h1>");
        out.close();
    }
} // /:

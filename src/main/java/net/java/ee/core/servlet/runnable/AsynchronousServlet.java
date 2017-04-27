package net.java.ee.core.servlet.runnable;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AsynchronousServlet", urlPatterns = {"/AsynchronousServlet"}, asyncSupported = true)
public class AsynchronousServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        final Logger logger = Logger.getLogger(AsynchronousServlet.class.getName());
        logger.log(Level.INFO, "--- Данные от doGet()");
        final AsyncContext asyncContext = request.startAsync();
        logger.log(Level.INFO, "---- Вызов asyncContext.start()");
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                int i = 0 ;
                logger.log(Level.INFO, "-----------------------------------------------Внутри потока = " + i);
                try {
                // Имитация длительного процесса.
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AsynchronousServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    asyncContext.getResponse().getWriter().println("You should see it after a short wait");
                    asyncContext.complete();
                } catch (IOException ex) {
                    Logger.getLogger(AsynchronousServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                i=i+1;
                logger.log(Level.INFO, "------------------------------------------------Покидаем поток = " + i);
            }
        });
        logger.log(Level.INFO, "Покидаем doGet()");
    }
}
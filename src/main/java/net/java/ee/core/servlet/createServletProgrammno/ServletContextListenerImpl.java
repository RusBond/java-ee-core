package net.java.ee.core.servlet.createServletProgrammno;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

// Программное конфигурирование веб-приложений - стр.80

@WebListener()
//@WebServlet(name = "ServletContextListenerImpl", urlPatterns = {"/ServletContextListenerImpl"})
public class ServletContextListenerImpl implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        try {
            ProgrammaticallyConfiguredServlet servlet = servletContext.createServlet(ProgrammaticallyConfiguredServlet.class);
            servletContext.addServlet("ProgrammaticallyConfiguredServlet", servlet);
            ServletRegistration servletRegistration = servletContext.getServletRegistration("ProgrammaticallyConfiguredServlet");
            servletRegistration.addMapping("/ProgrammaticallyConfiguredServlet");
        } catch (ServletException servletException) {
            servletContext.log(servletException.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}
package net.java.ee.core.servlet.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;


//  ServletContextListener
//Содержит методы для обработки событий ини-
//циализации контекста и уничтожения
//
//  ServletContextAttributeListener
//Содержит методы для реагирования на добав-
//ление, удаление или замену любых атрибутов
//в контексте сервлета (контекст приложения)
//
//  ServletRequestListener
//Содержит методы для обработки событий ини-
//циализации и уничтожения запросов
//
//  ServletRequestAttributeListener
//Содержит методы для реагирования на добав-
//ление, удаление или замену атрибутов в за-
//просе
//
//  HttpSessionListener
//Содержит методы для обработки событий ини-
//циализации HTTP-сеансов и уничтожения
//
//  HttpSessionAttributeListener
//Содержит методы для реагирования на добав-
//ление, удаление или

@WebListener()
@WebServlet(name = "HttpRequestListener", urlPatterns = {"/HttpRequestListener"})
public class HttpRequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletContext servletContext = servletRequestEvent.getServletContext();
        servletContext.log("Инициализирован новый запрос");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ServletContext servletContext = servletRequestEvent.getServletContext();
        servletContext.log("Запрос уничтожен");
    }
}
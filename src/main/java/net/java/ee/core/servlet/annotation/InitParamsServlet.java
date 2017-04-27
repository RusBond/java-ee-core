package net.java.ee.core.servlet.annotation;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(
        name = "InitParamsServlet",
        urlPatterns = {"/InitParamsServlet"},
        initParams = {
                @WebInitParam(name = "param1", value = "value1"),
                @WebInitParam(name = "param2", value = "value2")
        }
)
public class InitParamsServlet extends HttpServlet {
//    ServletConfig, с помощью которого контейнер сервлетов передает
//    информацию сервлету в процессе его инициализации
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        //возвращает объект, содержащий параметры конфигурации сервлета;

//        ServletContext - это ресурс, который доступен в пределах сессии или в
//        объекте HttpSession, который можно получить из HttpServletRequest,
//        который доступен во всех классах участвующих в обработке http запросов,
//        соответственно ServletContext принято использовать как session scope ресурс
//        в контроллере или представлении стандартных MVC приложений.
        ServletConfig servletConfig = getServletConfig();

        // TODO СМОТРЕТЬ В SimpleFilter
        String param1Val = servletConfig.getInitParameter("param1");
        String param2Val = servletConfig.getInitParameter("param2");

        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        printWriter.println("<p>");
        printWriter.println("The value of param1 is " + param1Val);
        printWriter.println("</p>");
        printWriter.println("<p>");
        printWriter.println("The value of param2 is " + param2Val);
        printWriter.println("</p>");
    }
}
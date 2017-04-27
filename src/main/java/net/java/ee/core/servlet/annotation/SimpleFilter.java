package net.java.ee.core.servlet.annotation;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;
import java.util.Enumeration;

//Фильтры были введены в спецификацию сервлета начиная с версии 2.3. Фильтр яв-
//        ляется объектом, который может динамически перехватывать запрос и манипули-
//        ровать его данными до того, как запрос будет обработан сервлетом. Фильтры также
//        могут манипулировать откликом после того, как свою работу завершит метод серв-
//        лета doGet() или doPost(), но прежде, чем вывод будет отправлен обозревателю.

@WebFilter(
        filterName = "SimpleFilter",
        initParams = {
                @WebInitParam(name = "filterparam1", value = "filtervalue1")
        },
        //URL, который будет перехватывать наш фильтр.
        urlPatterns = {"/InitParamsServlet"}
)
public class SimpleFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        System.out.println("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
//        ServletContext - это ресурс, который доступен в пределах сессии или в
//        объекте HttpSession, который можно получить из HttpServletRequest,
//        который доступен во всех классах участвующих в обработке http запросов,
//        соответственно ServletContext принято использовать как session scope ресурс
//        в контроллере или представлении стандартных MVC приложений.
        ServletContext servletContext = filterConfig.getServletContext();
        servletContext.log("Входим в doFilter()");
        servletContext.log("Инициализируем параметры: ");
        System.out.println("Входим в doFilter()");
        System.out.println("Инициализируем параметры: ");
        Enumeration<String> initParameterNames = filterConfig.getInitParameterNames();
        String parameterName;
        String parameterValue;
        while (initParameterNames.hasMoreElements())
        {
            parameterName = initParameterNames.nextElement();
            parameterValue = filterConfig.getInitParameter(parameterName);
            servletContext.log(parameterName + " = " + parameterValue);
            System.out.println(parameterName + " = " + parameterValue);
        }
        servletContext.log("Вызываем сервлет...");
        System.out.println("Вызываем сервлет...");
        filterChain.doFilter(servletRequest, servletResponse);
        servletContext.log("Возвращаемся из вызова сервлета");
        System.out.println("Возвращаемся из вызова сервлета");

    }

    @Override
    public void destroy() {
        filterConfig = null;
        System.out.println("destroy()");
    }
}
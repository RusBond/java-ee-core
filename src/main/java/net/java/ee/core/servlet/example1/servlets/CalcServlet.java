/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.java.ee.core.servlet.example1.servlets;

import net.java.ee.core.servlet.example1.calc.CalcOperations;
import net.java.ee.core.servlet.example1.calc.OperationType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "CalcServlet", urlPatterns = {"/CalcServlet"})
public class CalcServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet CalcServlet</title>");
        out.println("</head>");
        out.println("<body>");

        try {

            // считывание параметров
            double one = Double.valueOf(request.getParameter("one").toString()).doubleValue();
            double two = Double.valueOf(request.getParameter("two").toString()).doubleValue();
            String opearation = String.valueOf(request.getParameter("operation"));

            // определение или создание сессии
            HttpSession session = request.getSession(true);

            //TODO Данный объект будет доступен любому сервлету в нашем приложении и будет сохраняться между сеансами.
//            ServletContext servletContext = getServletContext();
//            Foo foo = new Foo(); // пример не существующего класса
//            servletContext.setAttribute("foo", foo);

            // получение типа операции
            OperationType operType = OperationType.valueOf(opearation.toUpperCase());

            // калькуляция
            double result = calcResult(operType, one, two);

            ArrayList<String> listOperations;
            // для новой сессии создаем новый список
            if (session.isNew()) {
                listOperations = new ArrayList<String>();
            } else { // иначе получаем список из атрибутов сессии
                listOperations = (ArrayList<String>) session.getAttribute("formula");
            }

            // добавление новой операции в список и атрибут сессии
            listOperations.add(one + " " + operType.getStringValue() + " " + two + " = " + result);
            session.setAttribute("formula", listOperations);


            // вывод всех операций
            out.println("<h1>ID вашей сессии равен: " + session.getId() + "</h1>");
            out.println("<h3>Список операций (всего:" + listOperations.size() + ") </h3>");

            for (String oper : listOperations) {
                out.println("<h3>" + oper + "</h3>");
            }
        } catch (Exception ex) {
            // предупреждение пользователю в случае ошибки
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);

        } finally {
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Сервлет - калькулятор";
    }// </editor-fold>


    // калькуляция
    private double calcResult(OperationType operType, double one, double two) {
        double result = 0;
        switch (operType) {
            case ADD: {
                result = CalcOperations.add(one, two);
                break;
            }
            case SUBTRACT: {
                result = CalcOperations.subtract(one, two);
                break;
            }

            case DIVIDE: {
                result = CalcOperations.divide(one, two);
                break;
            }

            case MULTIPLY: {
                result = CalcOperations.multiply(one, two);
                break;
            }

        }
        return result;
    }
}

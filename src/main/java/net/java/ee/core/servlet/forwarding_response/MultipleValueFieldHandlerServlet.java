package net.java.ee.core.servlet.forwarding_response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
public class MultipleValueFieldHandlerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String[] selectedOptions = request.getParameterValues("options");
        ArrayList<String> selectedOptionLabels = null;
        if (selectedOptions != null) {
            selectedOptionLabels = new ArrayList<String>(selectedOptions.length);
            for (String selectedOption : selectedOptions) {
                if (selectedOption.equals("options1")) {
                    selectedOptionLabels.add("option1");
                    System.out.println("option1");
                }
                else if (selectedOption.equals("options2")) {
                    selectedOptionLabels.add("option2");
                    System.out.println("option2");
                }
                else if (selectedOption.equals("options3")) {
                    selectedOptionLabels.add("option3");
                    System.out.println("option3");
                }
            }
        }
        //присоединения ArrayList к запросу
        request.setAttribute("checkedLabels", selectedOptionLabels);
        try {
            //После присоединения ArrayList к запросу мы переадресуем запрос к новому серв-
            //лету, используя следующую строку программы:
            request.getRequestDispatcher("ConfirmationServlet").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

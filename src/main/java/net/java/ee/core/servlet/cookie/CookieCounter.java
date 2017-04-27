package net.java.ee.core.servlet.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class CookieCounter {

    /* константа, которая будет использована для установки максимального
    времени жизни cookie (здесь указано 30 дней) */
    public static final int MAX_AGE_COOKIE = 60 * 60 * 24 * 30;

    public static void printToBrowser(HttpServletResponse response, HttpServletRequest request) {

        try {
            Writer out = response.getWriter();
            out.write("My Cookie counter: ");
            /* устанавливает счетчик количества вызовов сервлета пользователем */
            out.write(String.valueOf(prepareCookieCounter(request,response)));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed: " + e);
        }
    }

    // обновляет в сookie счетчик обращений пользователя к сервлету
    private static int prepareCookieCounter(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Cookie counterCookie;
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if ("counter".equals(cookies[i].getName())) {
                    String counterStr = cookies[i].getValue();
                    int counterValue;
                    try {
                        counterValue = Integer.parseInt(counterStr);
                    } catch (NumberFormatException e) {
                        counterValue = 0;
                    }
                    counterValue++;
                    counterCookie = new Cookie("counter", String.valueOf(counterValue));
                    counterCookie.setMaxAge(MAX_AGE_COOKIE);
                    response.addCookie(counterCookie);
                    return counterValue;
                }

            }

        }
        counterCookie = new Cookie("counter", "1");
        counterCookie.setMaxAge(MAX_AGE_COOKIE);
        response.addCookie(counterCookie);
        return 1;
    }
}
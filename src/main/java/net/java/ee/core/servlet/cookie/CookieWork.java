package net.java.ee.core.servlet.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CookieWork {

    public static void setCookie(HttpServletResponse resp) {
        String name = "Spiridonov";
        String role = "MegaAdmin";
        Cookie cookie = new Cookie(name, role);
        cookie.setMaxAge(3600);// время жизни файла
        resp.addCookie(cookie);

    }

    public static void printToBrowser(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                out.print("Number cookies :" + cookies.length + "<BR>");
                for (int i = 0; i < cookies.length; i++) {
                    Cookie cookie = cookies[i];
                    out.print("Secure :" + cookie.getSecure() + "<br>");
                    out.print(cookie.getName() + " = " + cookie.getValue() + "<br>");
                }
            }
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.toString());
        }

    }

}
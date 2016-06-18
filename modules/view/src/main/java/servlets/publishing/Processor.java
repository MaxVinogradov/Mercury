package servlets.publishing;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Processor {

    public int getUserIdFromSession(HttpServletRequest request) {
        return  (int)(request.getSession().getAttribute("user_id"));
    }

    public void doForward(HttpServletRequest request, HttpServletResponse response,
                          String page, String key, String value)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        request.setAttribute(key, value);
        dispatcher.forward(request, response);
    }

}

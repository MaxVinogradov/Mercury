package nc.sumy.edu.webapp.pageProcessors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractProcessor {

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

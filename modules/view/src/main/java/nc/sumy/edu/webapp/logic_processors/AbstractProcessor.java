package nc.sumy.edu.webapp.logic_processors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.String.valueOf;

public abstract class AbstractProcessor {

    public int getUserIdFromSession(HttpServletRequest request) {
        return  (int)(request.getSession().getAttribute("user_id"));
    }

    public void doForward(HttpServletRequest request, HttpServletResponse response,
                          Object page,
                          Object attributeKey,
                          Object attributeValue)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(valueOf(page));
        request.setAttribute(valueOf(attributeKey), valueOf(attributeValue));
        dispatcher.forward(request, response);
    }

}

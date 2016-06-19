package nc.sumy.edu.webapp.pageProcessors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public abstract class AbstractProcessor {

    public int getUserIdFromSession(HttpServletRequest request) {
        return  (int)(request.getSession().getAttribute("user_id"));
    }

    public void doForward(HttpServletRequest request, HttpServletResponse response,
                          String page)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    public void doForward(HttpServletRequest request, HttpServletResponse response,
                          String page,
                          String attributeKey,
                          String attributeValue)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        request.setAttribute(attributeKey, attributeValue);
        dispatcher.forward(request, response);
    }

    public void doForward(HttpServletRequest request, HttpServletResponse response,
                          String page,
                          Map<String, String> attributes)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        for (Map.Entry<String, String> attribute: attributes.entrySet()) {
            request.setAttribute(attribute.getKey(), attribute.getValue());
        }
        dispatcher.forward(request, response);
    }

}

package nc.sumy.edu.webapp.logic_processors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

public class AjaxProcessor {

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (nonNull(request.getSession(false))) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("exist");
        }
    }

}

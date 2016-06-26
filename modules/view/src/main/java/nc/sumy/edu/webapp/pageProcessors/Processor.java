package nc.sumy.edu.webapp.pageProcessors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Processor {

    int getUserIdFromSession(HttpServletRequest request);

    void doForward(HttpServletRequest request,
                   HttpServletResponse response,
                   String page,
                   String key, String value)
            throws ServletException, IOException;

    void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

}

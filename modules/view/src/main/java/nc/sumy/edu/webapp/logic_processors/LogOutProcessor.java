package nc.sumy.edu.webapp.logic_processors;

import nc.sumy.edu.webcontainer.action.Act;
import nc.sumy.edu.webcontainer.action.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;
import static nc.sumy.edu.webapp.constants.PageURLs.INDEX_PAGE;

@Act(id = "log_out")
public class LogOutProcessor implements Actions {

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (nonNull(request.getSession(false))) {
            request.getSession().invalidate();
            response.sendRedirect("/view" + INDEX_PAGE);
        }
    }

}

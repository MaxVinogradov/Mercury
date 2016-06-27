package nc.sumy.edu.webapp.logic_processors;

import nc.sumy.edu.webcontainer.action.Act;
import nc.sumy.edu.webcontainer.action.Actions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;

@Act(id = "isSessionExist")
public class AjaxProcessor implements Actions {

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (nonNull(request.getSession().getAttribute("user_id"))) {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("exist");
        }
    }

}
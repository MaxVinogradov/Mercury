package nc.sumy.edu.webapp.logic_processors;

import nc.sumy.edu.webapp.orm.LoadingServiceImpl;
import nc.sumy.edu.webapp.orm.domain.User;
import nc.sumy.edu.webapp.html_builders.HtmlCreatorImpl;
import nc.sumy.edu.webcontainer.action.Act;
import nc.sumy.edu.webcontainer.action.Actions;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;
import static nc.sumy.edu.webapp.constants.PageURLs.*;
import static nc.sumy.edu.webapp.constants.Attributes.*;

@Act(id = "log_in")
public class LogInProcessor implements Actions {

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN.toString());
        String password = request.getParameter(PASSWORD.toString());
        User user = (new LoadingServiceImpl()).loadUser(login);
        if (isNull(user)) {
            (new BasicProcessor()).doForward(request, response,
                    LOG_IN_PAGE,
                    LOGIN_ERROR,
                    (new HtmlCreatorImpl()).createErrorMassage("Invalid login. Try again!")
            );
        } else if (StringUtils.equals(user.getPassword(), password)){
            request.getSession().setAttribute(USER_ID.toString(), user.getUserId());
            response.sendRedirect("/view" + CREATE_POST_PAGE);
        } else {
            (new BasicProcessor()).doForward(request, response,
                    LOG_IN_PAGE,
                    LOGIN_ERROR,
                    (new HtmlCreatorImpl()).createErrorMassage("Uncorrected password. Try again!")
            );
        }
    }

}

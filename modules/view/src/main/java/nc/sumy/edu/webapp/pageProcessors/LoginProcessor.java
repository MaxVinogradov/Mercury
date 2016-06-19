package nc.sumy.edu.webapp.pageProcessors;

import nc.sumy.edu.webapp.orm.LoadingServiceImpl;
import nc.sumy.edu.webapp.orm.domain.User;
import nc.sumy.edu.webapp.viewProcessors.HtmlCreatorImpl;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;
import static nc.sumy.edu.webapp.enums.PageURLs.*;
import static nc.sumy.edu.webapp.enums.Attributes.*;

public class LoginProcessor extends AbstractProcessor {

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getAttribute(LOGIN.toString());
        String password = (String) request.getAttribute(PASSWORD.toString());
        User user = (new LoadingServiceImpl()).loadUser(login);
        if (isNull(user)) {
            doForward(request, response,
                    LOG_IN_PAGE.toString(),
                    LOGIN_ERROR.toString(),
                    (new HtmlCreatorImpl()).createErrorMassage("Invalid login. Try again!"));
        } else if (StringUtils.equals(user.getPassword(), password)){
            doForward(request, response,
                    LOG_IN_PAGE.toString(),
                    LOGIN_ERROR.toString(),
                    (new HtmlCreatorImpl()).createErrorMassage("Uncorrected password. Try again!"));
        } else {
            request.getSession().setAttribute(USER_ID.toString(), user.getUserId());
            doForward(request, response, CREATE_POST_PAGE.toString());
        }
    }

}

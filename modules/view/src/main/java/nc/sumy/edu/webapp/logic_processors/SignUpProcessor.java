package nc.sumy.edu.webapp.logic_processors;

import nc.sumy.edu.webapp.orm.LoadingServiceImpl;
import nc.sumy.edu.webapp.orm.StoringServiceImpl;
import nc.sumy.edu.webapp.orm.domain.User;
import nc.sumy.edu.webapp.html_builders.HtmlCreatorImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.util.Locale.*;
import static java.util.Objects.*;
import static nc.sumy.edu.webapp.constants.Attributes.*;
import static nc.sumy.edu.webapp.constants.PageURLs.*;

public class SignUpProcessor extends AbstractProcessor {

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN.toString());
        if (nonNull((new LoadingServiceImpl()).loadUser(login))) {
            doForward(request, response,
                    SIGN_UP_PAGE,
                    SIGN_UP_ERROR,
                    (new HtmlCreatorImpl()).createErrorMassage("This login has already exist. Try again!")
            );
        } else {
            User user = new User();
            DateFormat format = new SimpleDateFormat("dd-MM-yyyy", ENGLISH);
            try {
                user.setLogin(login)
                        .setPassword(request.getParameter(PASSWORD.toString()))
                        .setMail(request.getParameter(MAIL.toString()))
                        .setBirthDate(format.parse(request.getParameter(BIRTH_DATE.toString())));

                request.getSession().setAttribute(USER_ID.toString(),
                        (new StoringServiceImpl()).addUser(user).getUserId());
                response.sendRedirect("/view" + CREATE_POST_PAGE.toString());
            } catch (ParseException e) {
                doForward(request, response,
                        SIGN_UP_PAGE,
                        SIGN_UP_ERROR,
                        (new HtmlCreatorImpl()).createErrorMassage("Incorrect birthday date. Try again!")
                );
            }
        }
    }

}

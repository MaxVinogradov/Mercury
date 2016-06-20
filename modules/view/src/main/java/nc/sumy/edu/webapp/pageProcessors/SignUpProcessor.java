package nc.sumy.edu.webapp.pageProcessors;

import nc.sumy.edu.webapp.orm.LoadingServiceImpl;
import nc.sumy.edu.webapp.orm.StoringServiceImpl;
import nc.sumy.edu.webapp.orm.domain.User;
import nc.sumy.edu.webapp.viewProcessors.HtmlCreatorImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.util.Locale.*;
import static java.util.Objects.*;
import static nc.sumy.edu.webapp.enums.Attributes.*;
import static nc.sumy.edu.webapp.enums.PageURLs.*;

public class SignUpProcessor extends AbstractProcessor {

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter(LOGIN.toString());
        System.err.println(login);
        if (nonNull((new LoadingServiceImpl()).loadUser(login))) {
            doForward(request, response,
                    SIGN_UP_PAGE,
                    SIGN_UP_ERROR,
                    (new HtmlCreatorImpl()).createErrorMassage("This login has already exist. Try again!")
            );
        } else {
            User user = new User();
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy", ENGLISH);
            try {
                user.setLogin(login)
                        .setPassword((String) request.getParameter(PASSWORD.toString()))
                        .setMail((String) request.getParameter(MAIL.toString()))
                        .setBirthDate(format.parse(String.valueOf(request.getParameter(BIRTH_DATE.toString()))));
                System.out.println(user.getLogin());
                System.out.println(user.getPassword());
                System.out.println(user.getMail());
                System.out.println(user.getBirthDate());
                doForward(request, response,
                        CREATE_POST_PAGE,
                        USER_ID,
                        (new StoringServiceImpl()).addUser(user).getUserId()
                );
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

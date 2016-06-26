package nc.sumy.edu.webapp.controller;

import nc.sumy.edu.webapp.logic_processors.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@WebServlet("/Controller")
public class MainController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (nonNull(action) && isNotEmpty(action)) {
            switch(action) {
                case ("sign_up") : {
                    (new SignUpProcessor()).process(request, response);
                }
                break;
                case ("log_in") : {
                    (new LogInProcessor()).process(request, response);
                }
                break;
                case ("log_out") : {
                    (new LogOutProcessor()).process(request, response);
                }
                break;
                case ("publish") : {
                    (new PublishingProcessor()).process(request, response);
                }
                break;
                case ("isSessionExist") : {
                    (new AjaxProcessor()).process(request, response);
                }
            }
        }
    }

}

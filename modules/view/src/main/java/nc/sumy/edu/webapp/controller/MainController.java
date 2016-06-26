package nc.sumy.edu.webapp.controller;

import nc.sumy.edu.webapp.logic_processor.LogInProcessor;
import nc.sumy.edu.webapp.logic_processor.PublishingProcessor;
import nc.sumy.edu.webapp.logic_processor.SignUpProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static nc.sumy.edu.webapp.enums.PageURLs.*;

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
                    if (nonNull(request.getSession(false))) {
                        request.getSession().invalidate();
                        response.sendRedirect("/view" + INDEX_PAGE);
                    }
                }
                break;
                case ("publish") : {
                    (new PublishingProcessor()).process(request, response);
                }
                break;
                case ("isSessionExist") : {
                    if (nonNull(request.getSession(false))) {
                        response.setContentType("text/plain");
                        response.setCharacterEncoding("UTF-8");
                        response.getWriter().write("exist");
                    }
                }
            }
        }
    }

    /*
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (nonNull(action) && isNotEmpty(action)) {
            switch(action) {
                case ("sign_up") : {
                    System.err.println("-------------------------!!1");
                    (new SignUpProcessor()).process(request, response);
                }
                break;
                case ("log_in") : {
                    (new LogInProcessor()).process(request, response);
                }
                break;
            }
        }
    }


    */
}

package nc.sumy.edu.webapp.controller;

import nc.sumy.edu.webcontainer.action.ActionHandlerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

@WebServlet("/Controller")
public class MainController extends HttpServlet  {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (nonNull(action) && isNotEmpty(action)) {
            (new ActionHandlerImpl()).process(action, request, response);
        }
    }

}

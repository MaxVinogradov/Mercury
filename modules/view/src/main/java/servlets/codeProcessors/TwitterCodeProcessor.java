package servlets.codeProcessors;

import nc.sumy.edu.webapp.integration.IntegrationImpl;
import nc.sumy.edu.webapp.orm.StoringService;
import nc.sumy.edu.webapp.orm.StoringServiceImpl;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworkInfo;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworks;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "TwitterCodeProcessor")
public class TwitterCodeProcessor extends HttpServlet {
    private static final CodeProcessor PROCESSOR = new CodeProcessor();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PROCESSOR.processOAuth1(request, response, SocialNetworks.TWITTER);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PROCESSOR.processOAuth1(request, response, SocialNetworks.TWITTER);
    }

}

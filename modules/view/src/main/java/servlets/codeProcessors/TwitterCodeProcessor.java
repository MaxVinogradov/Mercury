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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        String requestToken = request.getParameter("requestToken");
        if (code != null && requestToken != null) {
            SocialNetworkInfo info = new IntegrationImpl().processCodeForOAuth1(SocialNetworks.TWITTER,
                    requestToken, code);
            StoringService store = new StoringServiceImpl();
            store.addAccount(info);
        }
        response.addHeader("Forward", "path to adding new networks page");
    }
}

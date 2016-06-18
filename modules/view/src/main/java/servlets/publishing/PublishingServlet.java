package servlets.publishing;

import nc.sumy.edu.webapp.integration.IntegrationImpl;
import nc.sumy.edu.webapp.orm.LoadingServiceImpl;
import nc.sumy.edu.webcontainer.common.integration.ResultOfPostSubmit;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworkInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "PublishingServlet")
public class PublishingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = (int) request.getSession().getAttribute("user_id");
        Set<SocialNetworkInfo> networkInfos = (Set) new LoadingServiceImpl().loadAccounts(id);
        String message = (String) request.getAttribute("message");
        Set<ResultOfPostSubmit> resultsInfos = new IntegrationImpl().submitPost(networkInfos, message);
        // process result of submitting
        for (ResultOfPostSubmit result : resultsInfos) {
            System.out.println("Posting to " + result.getInfo().getLogin() +
                    (result.isPostSucceed() ? " was successful." : "failed."));
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/create_post.jsp");
        dispatcher.forward(request, response);
    }
}

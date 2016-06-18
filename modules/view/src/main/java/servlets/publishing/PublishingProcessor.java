package servlets.publishing;

import nc.sumy.edu.webapp.integration.IntegrationImpl;
import nc.sumy.edu.webapp.orm.LoadingServiceImpl;
import nc.sumy.edu.webcontainer.common.integration.ResultOfPostSubmit;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworkInfo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class PublishingProcessor {

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = (int) request.getSession().getAttribute("user_id");
        Set<SocialNetworkInfo> networkInfos = (Set<SocialNetworkInfo>) new LoadingServiceImpl().loadAccounts(id);
        String message = (String) request.getAttribute("message");
        getTableData(new IntegrationImpl().submitPost(networkInfos, message));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/create_post.jsp");
        dispatcher.forward(request, response);
    }

    private String getTableData(Set<ResultOfPostSubmit> resultsInfos) {
        StringBuilder builder = new StringBuilder();
        HtmlProcessor processor = new HtmlProcessor();
        for (ResultOfPostSubmit result : resultsInfos) {
            builder.append(processor.createTableRow(result.getInfo().getLogin(),
                    (result.isPostSucceed() ? " was successful" : "failed")));
        }
        return builder.toString();
    }
}

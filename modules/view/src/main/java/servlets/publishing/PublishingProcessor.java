package servlets.publishing;

import nc.sumy.edu.webapp.integration.IntegrationImpl;
import nc.sumy.edu.webapp.orm.LoadingServiceImpl;
import nc.sumy.edu.webcontainer.common.integration.ResultOfPostSubmit;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworkInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class PublishingProcessor extends Processor{

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = getUserIdFromSession(request);
        Set<SocialNetworkInfo> networkInfos = (Set<SocialNetworkInfo>) new LoadingServiceImpl().loadAccounts(userId);
        String message = (String) request.getAttribute("message"); //TODO!!!
        doForward(request, response, "/create_post.jsp",
                "posting_results", getTableData(new IntegrationImpl().submitPost(networkInfos, message)));
    }

    private String getTableData(Set<ResultOfPostSubmit> resultsInfos) {
        StringBuilder builder = new StringBuilder();
        HtmlProcessor processor = new HtmlProcessor();
        for (ResultOfPostSubmit result : resultsInfos) {
            builder.append(processor.createTableRow(result.getInfo().getLogin(),
                    (result.isPostSucceed() ? processor.createGlyphicon("ok") : processor.createGlyphicon("remove"))));
        }
        return builder.toString();
    }
}

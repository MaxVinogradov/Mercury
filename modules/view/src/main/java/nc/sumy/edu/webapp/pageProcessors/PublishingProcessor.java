package nc.sumy.edu.webapp.pageProcessors;

import nc.sumy.edu.webapp.integration.IntegrationImpl;
import nc.sumy.edu.webapp.orm.LoadingServiceImpl;
import nc.sumy.edu.webapp.orm.StoringService;
import nc.sumy.edu.webapp.orm.StoringServiceImpl;
import nc.sumy.edu.webapp.orm.domain.Post;
import nc.sumy.edu.webapp.viewProcessors.HtmlCreatorImpl;
import nc.sumy.edu.webcontainer.common.integration.ResultOfPostSubmit;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworkInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

import static nc.sumy.edu.webapp.enums.PageURLs.*;
import static nc.sumy.edu.webapp.enums.Attributes.*;

public class PublishingProcessor extends AbstractProcessor {

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Set<SocialNetworkInfo> networkInfos =
                (Set<SocialNetworkInfo>) new LoadingServiceImpl().loadAccounts(getUserIdFromSession(request));
        String message = (String) request.getAttribute(MESSAGE.toString());
        Post post = new Post();
        post.setUserId(getUserIdFromSession(request))
                .setTitle("")
                .setBody(message)
                .setPublishDate(new Date());
        StoringService storingService = new StoringServiceImpl();
        storingService.addPost(post);
        doForward(request, response,
                CREATE_POST_PAGE.toString(),
                POSTING_RESULTS.toString(),
                getTableData(new IntegrationImpl().submitPost(networkInfos, message)));
    }

    private String getTableData(Set<ResultOfPostSubmit> resultsInfos) {
        StringBuilder builder = new StringBuilder();
        HtmlCreatorImpl processor = new HtmlCreatorImpl();
        for (ResultOfPostSubmit result : resultsInfos) {
            builder.append(processor.createTableRow(result.getInfo().getLogin(),
                    (result.isPostSucceed() ? processor.createGlyphicon("ok") : processor.createGlyphicon("remove"))));
        }
        return builder.toString();
    }


}

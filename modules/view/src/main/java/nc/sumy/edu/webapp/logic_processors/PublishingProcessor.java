package nc.sumy.edu.webapp.logic_processors;

import nc.sumy.edu.webapp.integration.IntegrationImpl;
import nc.sumy.edu.webapp.orm.LoadingServiceImpl;
import nc.sumy.edu.webapp.orm.StoringService;
import nc.sumy.edu.webapp.orm.StoringServiceImpl;
import nc.sumy.edu.webapp.orm.domain.Post;
import nc.sumy.edu.webapp.html_builders.HtmlCreatorImpl;
import nc.sumy.edu.webcontainer.common.integration.ResultOfPostSubmit;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworkInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

import static nc.sumy.edu.webapp.constants.PageURLs.*;
import static nc.sumy.edu.webapp.constants.Attributes.*;

public class PublishingProcessor extends AbstractProcessor {

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Set<SocialNetworkInfo> networkInfos =
                (Set<SocialNetworkInfo>) new LoadingServiceImpl().loadAccountsWithTwoMethods(getUserIdFromSession(request));
        String message = request.getParameter(MESSAGE.toString());
        Post post = new Post();
        post.setUserId(getUserIdFromSession(request))
                .setTitle("")
                .setBody(message)
                .setPublishDate(new Date());
        StoringService storingService = new StoringServiceImpl();
        storingService.addPost(post);
        doForward(request, response,
                CREATE_POST_PAGE,
                POSTING_RESULTS,
                getTableData(new IntegrationImpl().submitPost(networkInfos, message))
        );
    }

    private StringBuilder getTableData(Set<ResultOfPostSubmit> resultsInfos) {
        StringBuilder builder = new StringBuilder();
        HtmlCreatorImpl processor = new HtmlCreatorImpl();
        for (ResultOfPostSubmit result : resultsInfos) {
            builder.append(processor.createTableRow(result.getInfo().getLogin(),
                    (result.isPostSucceed() ? processor.createGlyphicon("ok") : processor.createGlyphicon("remove"))));
        }
        return builder;
    }


}

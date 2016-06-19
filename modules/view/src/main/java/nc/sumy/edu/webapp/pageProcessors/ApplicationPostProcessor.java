package nc.sumy.edu.webapp.pageProcessors;

import nc.sumy.edu.webapp.orm.LoadingServiceImpl;
import nc.sumy.edu.webapp.viewProcessors.HtmlCreatorImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static nc.sumy.edu.webapp.enums.PageURLs.*;
import static nc.sumy.edu.webapp.enums.Attributes.*;

public class ApplicationPostProcessor extends AbstractProcessor {

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HtmlCreatorImpl processor = new HtmlCreatorImpl();
        doForward(request, response,
                APP_POSTS_PAGE.toString(),
                APPLICATION_POSTS.toString(),
                processor.createPostList(new LoadingServiceImpl().loadPosts(getUserIdFromSession(request))));
    }

}

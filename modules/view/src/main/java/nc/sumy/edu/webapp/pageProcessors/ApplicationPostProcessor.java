package nc.sumy.edu.webapp.pageProcessors;

import nc.sumy.edu.webapp.orm.LoadingServiceImpl;
import nc.sumy.edu.webapp.viewProcessors.HtmlCreatorImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApplicationPostProcessor extends AbstractProcessor {

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HtmlCreatorImpl processor = new HtmlCreatorImpl();
        doForward(request, response,
                "/show_post.jsp",
                "application_posts",
                processor.createPostList(new LoadingServiceImpl().loadPosts(getUserIdFromSession(request))));
    }

}

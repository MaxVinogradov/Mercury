package servlets.publishing;

import nc.sumy.edu.webapp.orm.LoadingServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApplicationPostProcessor extends Processor{

    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HtmlProcessor processor = new HtmlProcessor();
        doForward(request, response,
                "/show_post.jsp",
                "application_posts",
                processor.createPostList(new LoadingServiceImpl().loadPosts(getUserIdFromSession(request))));
    }

}

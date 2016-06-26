package nc.sumy.edu.webapp.logic_processor;

import nc.sumy.edu.webapp.orm.LoadingServiceImpl;
import nc.sumy.edu.webapp.html_processors.HtmlCreatorImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ApplicationPostProcessor extends AbstractProcessor {

    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return (new HtmlCreatorImpl()).createPostList(new LoadingServiceImpl().loadPosts(getUserIdFromSession(request)));
    }

}

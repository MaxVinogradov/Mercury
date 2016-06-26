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

    public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return (new HtmlCreatorImpl()).createPostList(new LoadingServiceImpl().loadPosts(getUserIdFromSession(request)));
    }

}

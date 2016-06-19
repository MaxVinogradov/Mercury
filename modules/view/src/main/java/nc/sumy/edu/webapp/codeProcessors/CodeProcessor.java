package nc.sumy.edu.webapp.codeProcessors;

import com.github.scribejava.core.exceptions.OAuthException;
import nc.sumy.edu.webapp.integration.IntegrationImpl;
import nc.sumy.edu.webapp.integration.exceptions.IntegrationException;
import nc.sumy.edu.webapp.orm.StoringService;
import nc.sumy.edu.webapp.orm.StoringServiceImpl;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworkInfo;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworks;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CodeProcessor {
    private static final String REDIRECT_URL = "connect.jsp";
    private static final String FAIL_PARAMETER = "?success=false";
    private static final String SUCCESS_PARAMETER = "?success=true";


    public void processOAuth1(HttpServletRequest request, HttpServletResponse response, SocialNetworks type) throws IOException {
        String code = request.getParameter("oauth_verifier");
        String requestToken = request.getParameter("oauth_token");
        String redirectUrl = REDIRECT_URL;
        Object user_idObj = request.getSession().getAttribute("user_id");
        System.out.println(user_idObj);
        System.out.println(redirectUrl);
        System.out.println(code);
        System.out.println(requestToken);
        if (code != null && requestToken != null && user_idObj != null) {
            try{
                int user_id = (int) user_idObj;
                SocialNetworkInfo info = new IntegrationImpl().processCodeForOAuth1(type,
                        requestToken, code);
                StoringService store = new StoringServiceImpl();
                store.addAccount(user_id, info);
                redirectUrl += SUCCESS_PARAMETER;
            } catch(IntegrationException e) {
                //log
                redirectUrl += FAIL_PARAMETER;
            }
        } else
            redirectUrl += FAIL_PARAMETER;
        response.sendRedirect(redirectUrl);
    }

    public void processOAuth2(HttpServletRequest request, HttpServletResponse response, SocialNetworks type) throws IOException, ServletException {
        String code = request.getParameter("code");
        String redirectUrl = REDIRECT_URL;
        Object user_idObj = request.getSession().getAttribute("user_id");
        System.out.println(user_idObj);
        System.out.println(redirectUrl);
        System.out.println(code);
        if (code != null && user_idObj != null) {
            try{
                int user_id = (int) user_idObj;
                SocialNetworkInfo info = new IntegrationImpl().processCodeForOAuth2(type, code);
                StoringService store = new StoringServiceImpl();
                store.addAccount(user_id, info);
                redirectUrl += SUCCESS_PARAMETER;
            } catch(IntegrationException e) {
                //log
                redirectUrl += FAIL_PARAMETER;
            }
        } else {
            redirectUrl += FAIL_PARAMETER;
        }
        //response.getWriter().append(redirectUrl);
        //response.sendRedirect(redirectUrl);
    }
}

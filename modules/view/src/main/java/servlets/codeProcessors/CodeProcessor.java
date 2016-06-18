package servlets.codeProcessors;

import nc.sumy.edu.webapp.integration.IntegrationImpl;
import nc.sumy.edu.webapp.integration.exceptions.IntegrationException;
import nc.sumy.edu.webapp.orm.StoringService;
import nc.sumy.edu.webapp.orm.StoringServiceImpl;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworkInfo;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworks;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CodeProcessor {
    private static final String REDIRECT_URL = "connect.jsp";
    private static final String FAIL_PARAMETER = "?success=false";


    public void processOAuth1(HttpServletRequest request, HttpServletResponse response, SocialNetworks type) {
        String code = request.getParameter("code");
        String requestToken = request.getParameter("requestToken");
        String redirectUrl = REDIRECT_URL;
        if (code != null && requestToken != null) {
            try{
                SocialNetworkInfo info = new IntegrationImpl().processCodeForOAuth1(type,
                        requestToken, code);
                StoringService store = new StoringServiceImpl();
                store.addAccount(info);
            } catch(IntegrationException e) {
                //log
                redirectUrl += FAIL_PARAMETER;
            }

        }
        response.addHeader("Forward", redirectUrl);
    }

    public void processOAuth2(HttpServletRequest request, HttpServletResponse response, SocialNetworks type) {
        String code = request.getParameter("code");
        String redirectUrl = REDIRECT_URL;
        if (code != null) {
            try{
                SocialNetworkInfo info = new IntegrationImpl().processCodeForOAuth2(type, code);
                StoringService store = new StoringServiceImpl();
                store.addAccount(info);
            } catch(IntegrationException e) {
                //log
                redirectUrl += FAIL_PARAMETER;
            }
        }
        response.addHeader("Forward", redirectUrl);
    }
}

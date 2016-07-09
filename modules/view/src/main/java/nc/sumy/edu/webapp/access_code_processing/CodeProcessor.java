package nc.sumy.edu.webapp.access_code_processing;

import nc.sumy.edu.webapp.integration.IntegrationImpl;
import nc.sumy.edu.webapp.integration.exceptions.IntegrationException;
import nc.sumy.edu.webapp.orm.LoadingServiceImpl;
import nc.sumy.edu.webapp.orm.StoringService;
import nc.sumy.edu.webapp.orm.StoringServiceImpl;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworkInfo;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworks;

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
        Object userIdObj = request.getSession().getAttribute("user_id");

        if (code == null || requestToken == null || userIdObj == null) {
            redirectUrl += FAIL_PARAMETER;
        } else {
            try{
                int userId = (int) userIdObj;
                SocialNetworkInfo info = new IntegrationImpl().processCodeForOAuth1(type,
                        requestToken, code);
                fixNullFields(info);
                setLogin(info, userId);
                StoringService store = new StoringServiceImpl();
                store.addAccount(userId, info);
                redirectUrl += SUCCESS_PARAMETER;
            } catch(IntegrationException e) {
                //log
                redirectUrl += FAIL_PARAMETER;
            }
        }
        response.sendRedirect(redirectUrl);
    }

    private void setLogin(SocialNetworkInfo info, int userId) {
        int size = new LoadingServiceImpl().loadAccountsWithTwoMethods(userId).size();
        info.setLogin(userId + "_" + (size + 1));
    }

    public void processOAuth2(HttpServletRequest request, HttpServletResponse response, SocialNetworks type) throws IOException, ServletException {
        String code = request.getParameter("code");
        String redirectUrl = REDIRECT_URL;
        Object userIdObj = request.getSession().getAttribute("user_id");

        if (code == null || userIdObj == null) {
            redirectUrl += FAIL_PARAMETER;
        } else {
            try{
                int userId = (int) userIdObj;
                SocialNetworkInfo info = new IntegrationImpl().processCodeForOAuth2(type, code);
                fixNullFields(info);
                setLogin(info,userId);
                StoringService store = new StoringServiceImpl();
                store.addAccount(userId, info);
                redirectUrl += SUCCESS_PARAMETER;
            } catch(IntegrationException e) {
                //log
                redirectUrl += FAIL_PARAMETER;
            }
        }
        response.sendRedirect(redirectUrl);
    }

    private void fixNullFields(SocialNetworkInfo info) {
        if (info.getAdditionalTokenField() == null) {
            info.setAdditionalTokenField("");
        }
        if (info.getLastToken() == null) {
            info.setLastToken("");
        }
        if (info.getLogin() == null) {
            info.setLogin("");
        }
        if (info.getPassword() == null) {
            info.setPassword("");
        }
    }
}

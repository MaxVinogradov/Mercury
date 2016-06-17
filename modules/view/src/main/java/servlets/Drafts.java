package servlets;


import nc.sumy.edu.webapp.integration.IntegrationImpl;
import nc.sumy.edu.webapp.orm.LoadingServiceImpl;
import nc.sumy.edu.webcontainer.common.integration.ResultOfPostSubmit;

import javax.servlet.http.HttpSession;
import java.util.Set;

public class Drafts {
    private void draftForPostingJsp(HttpSession session, String message) {
        Integer id = (Integer) session.getAttribute("user_id");
        Set networkInfos = (Set) new LoadingServiceImpl().loadAccounts(id);
        Set<ResultOfPostSubmit> resultsInfos = new IntegrationImpl().submitPost(networkInfos, message);
        // process result of submitting
        for (ResultOfPostSubmit result : resultsInfos) {
            System.out.println("Posting to " + result.getInfo().getLogin() +
                    (result.isPostSucceed() ? " was successful." : "failed."));
        }
    }
}

package nc.sumy.edu.webapp.integration;

import nc.sumy.edu.webcontainer.common.integration.ResultOfPostSubmit;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworkInfo;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworks;

import java.util.Set;

public interface Integration {


    Set<ResultOfPostSubmit> submitPost(Set<SocialNetworkInfo> networkInfoSet, String message);

    SocialNetworkInfo processCodeForOAuth2(SocialNetworks type, String code);

    SocialNetworkInfo processCodeForOAuth1(SocialNetworks type, String requestCode, String code);

    String getAuthorisationUrlForNetwork(SocialNetworks type);
}

package nc.sumy.edu.webapp.integration;

import java.util.Set;

public interface Integration {



    boolean publishPost(String message);

    SocialNetworkInfo processCodeForOAuth2(SocialNetworks type, String code);

    SocialNetworkInfo processCodeForOAuth1(SocialNetworks type, String requestCode, String code);

    String getAuthorisationUrlForNetwork(SocialNetworks type);

    void setConnectedNetworks(Set<SocialNetworkInfo> networkInfoSet);
}

package nc.sumy.edu.webapp.integration;

import java.util.Set;

public interface Integration {



    boolean publishPost(String message);

    SocialNetworkInfo processCode(SocialNetworks type, String code);

    String getAuthorisationUrlForNetwork(SocialNetworks type);

    void connect(Set<SocialNetworkInfo> networkInfoSet);
}

package nc.sumy.edu.webapp.integration;

import nc.sumy.edu.webcontainer.common.integration.SocialNetworkInfo;

public interface SocialNetworkIntegration {

    String getAuthorisationUrl();

    boolean post(SocialNetworkInfo info, String message);
}

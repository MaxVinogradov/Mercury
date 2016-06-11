package nc.sumy.edu.webapp.integration;

import nc.sumy.edu.webapp.integration.common.SocialNetworkInfo;

public interface SocialNetworkIntegration {

    String getAuthorisationUrl();

    boolean post(SocialNetworkInfo info, String message);
}

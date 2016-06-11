package nc.sumy.edu.webapp.integration.oauth2;


import nc.sumy.edu.webapp.integration.common.SocialNetworkInfo;
import nc.sumy.edu.webapp.integration.SocialNetworkIntegration;

public interface OAuth2Integration extends SocialNetworkIntegration {
    SocialNetworkInfo getAccessTokenByCode(String code);
}

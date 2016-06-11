package nc.sumy.edu.webapp.integration.oauth1;


import nc.sumy.edu.webapp.integration.common.SocialNetworkInfo;
import nc.sumy.edu.webapp.integration.SocialNetworkIntegration;

public interface OAuth1Integration extends SocialNetworkIntegration {
    SocialNetworkInfo getAccessTokenByCode(String requestCode, String code);
}

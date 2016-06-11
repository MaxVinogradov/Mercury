package nc.sumy.edu.webapp.integration;


public interface OAuth2Integration extends SocialNetworkIntegration {
    SocialNetworkInfo getAccessTokenByCode(String code);
}

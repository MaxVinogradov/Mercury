package nc.sumy.edu.webapp.integration;


public interface SocialNetworkIntegration {

    String getAuthorisationUrl();

    SocialNetworkInfo getAccessTokenByCode();

    boolean post(String token, String message);
}

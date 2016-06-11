package nc.sumy.edu.webapp.integration;


import com.github.scribejava.core.model.OAuth2AccessToken;

public interface SocialNetworkIntegration {

    String getAuthorisationUrl();

    boolean post(SocialNetworkInfo info, String message);

    boolean post(OAuth2AccessToken token, String message);
}

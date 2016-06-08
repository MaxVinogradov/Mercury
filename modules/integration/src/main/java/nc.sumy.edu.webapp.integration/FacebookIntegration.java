package nc.sumy.edu.webapp.integration;


import com.github.scribejava.core.model.OAuth2AccessToken;

public class FacebookIntegration implements SocialNetworkIntegration{

    @Override
    public String getAuthorisationUrl() {
        return null;
    }

    @Override
    public SocialNetworkInfo getAccessTokenByCode(String code) {
        return null;
    }

    @Override
    public boolean post(SocialNetworkInfo info, String message) {
        return false;
    }

    @Override
    public boolean post(OAuth2AccessToken token, String message) {
        return false;
    }


}

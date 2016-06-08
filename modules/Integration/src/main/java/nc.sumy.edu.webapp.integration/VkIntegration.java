package nc.sumy.edu.webapp.integration;

public class VkIntegration implements SocialNetworkIntegration {

    @Override
    public String getAuthorisationUrl() {
        return null;
    }

    @Override
    public SocialNetworkInfo getAccessTokenByCode() {
        return null;
    }

    @Override
    public boolean post(String token, String message) {
        return false;
    }
}

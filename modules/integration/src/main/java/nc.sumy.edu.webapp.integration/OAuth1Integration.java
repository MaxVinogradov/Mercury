package nc.sumy.edu.webapp.integration;


public interface OAuth1Integration extends SocialNetworkIntegration{
    SocialNetworkInfo getAccessTokenByCode(String requestCode, String code);
}

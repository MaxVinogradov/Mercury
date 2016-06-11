package nc.sumy.edu.webapp.integration;

public interface SocialNetworkIntegration {

    String getAuthorisationUrl();

    boolean post(SocialNetworkInfo info, String message);
}

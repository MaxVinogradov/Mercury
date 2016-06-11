package nc.sumy.edu.webapp.integration;

public class IntegrationNotFoundException extends IntegrationException {
    public IntegrationNotFoundException(SocialNetworks type) {
        super("Integration for social network with name " + type + "was not found");
    }

}

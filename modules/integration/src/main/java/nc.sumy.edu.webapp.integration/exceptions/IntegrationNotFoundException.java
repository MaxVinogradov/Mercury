package nc.sumy.edu.webapp.integration.exceptions;

import nc.sumy.edu.webcontainer.common.integration.SocialNetworks;

public class IntegrationNotFoundException extends IntegrationException {
    public IntegrationNotFoundException(SocialNetworks type) {
        super("Integration for social network with name " + type + "was not found");
    }

}

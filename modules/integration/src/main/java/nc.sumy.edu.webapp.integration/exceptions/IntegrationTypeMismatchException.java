package nc.sumy.edu.webapp.integration.exceptions;

import nc.sumy.edu.webcontainer.common.integration.SocialNetworks;

public class IntegrationTypeMismatchException extends IntegrationException{
    public IntegrationTypeMismatchException(SocialNetworks type, String desirable) {
        super("Integration for social network with name " + type + " does not use protocol " + desirable);
    }
}

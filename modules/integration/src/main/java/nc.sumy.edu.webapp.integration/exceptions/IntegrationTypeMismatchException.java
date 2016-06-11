package nc.sumy.edu.webapp.integration.exceptions;

import nc.sumy.edu.webapp.integration.common.SocialNetworks;

public class IntegrationTypeMismatchException extends IntegrationException{
    public IntegrationTypeMismatchException(SocialNetworks type, String desirable) {
        super("Integration for social network with name " + type + " does not use protocol " + desirable);
    }
}

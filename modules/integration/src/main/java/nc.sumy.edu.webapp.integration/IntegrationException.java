package nc.sumy.edu.webapp.integration;

public class IntegrationException extends RuntimeException {
    public IntegrationException(String message) {
        super("Exception in the integration module appeared: " + message);
    }
}

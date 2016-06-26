package nc.sumy.edu.webapp.integration.exceptions;

public class IntegrationException extends RuntimeException {
    public IntegrationException(String message) {
        super("Exception in the integration module appeared: " + message);
    }
    public IntegrationException(String message, Throwable throwable) {
        super("Exception in the integration module appeared: " + message, throwable);
    }
}

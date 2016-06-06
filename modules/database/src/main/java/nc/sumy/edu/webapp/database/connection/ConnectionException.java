package nc.sumy.edu.webapp.database.connection;

public class ConnectionException extends DataBaseException {

    public ConnectionException(Throwable cause) {
        super("Cannot get connection", cause);
    }
}

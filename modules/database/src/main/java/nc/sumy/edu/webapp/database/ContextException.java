package nc.sumy.edu.webapp.database;

import java.util.Map;

public class ContextException extends DataBaseException {

    public ContextException(Map environment, Throwable cause) {
        super("Cannot get context from environment with parameters: "
                + environment, cause);
    }
}

package nc.sumy.edu.webapp.database;

public class DataSourceException extends DataBaseException {

    public DataSourceException(String dataSource, Throwable cause) {
        super("Cannot find " + dataSource + " from context", cause);
    }
}

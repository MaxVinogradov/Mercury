package nc.sumy.edu.webapp.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class DataSourceConnection implements DataBaseConnection {
    private static final String INITIAL_CONTEXT_FACTORY =
            "weblogic.jndi.WLInitialContextFactory";
    private static final String PROVIDER_URL =
            "t3://localhost:7001";

    private final DataSource dataSource;

    public DataSourceConnection(String dataSourceName) {
        dataSource = getDataSource(dataSourceName);
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }

    private static DataSource getDataSource(String dataSource) {
        Hashtable environment = new Hashtable();
        environment.put(Context.INITIAL_CONTEXT_FACTORY,
                INITIAL_CONTEXT_FACTORY);
        environment.put(Context.PROVIDER_URL, PROVIDER_URL);
        Context ctx = null;
        try {
            ctx = new InitialContext(environment);
        } catch (NamingException e) {
            throw new ContextException(environment, e);
        }
        try {
            return (DataSource) ctx.lookup(dataSource);
        } catch (NamingException e) {
            throw new DataSourceException(dataSource, e);
        }
    }
}
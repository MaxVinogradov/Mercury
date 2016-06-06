package nc.sumy.edu.webapp.database.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import static javax.naming.Context.INITIAL_CONTEXT_FACTORY;
import static javax.naming.Context.PROVIDER_URL;


public class DataBaseConnectionOracle implements DataBaseConnection {
    private static final String WEBLOGIC_INITIAL_CONTEXT_FACTORY =
            "weblogic.jndi.WLInitialContextFactory";
    private static final String WEBLOGIC_PROVIDER_URL =
            "t3://localhost:7001";

    private final DataSource dataSource;

    public DataBaseConnectionOracle(String dataSourceName) {
        dataSource = getDataSource(dataSourceName);
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }

    private DataSource getDataSource(String dataSource) {
        Hashtable environment = new Hashtable();
        environment.put(INITIAL_CONTEXT_FACTORY,
                WEBLOGIC_INITIAL_CONTEXT_FACTORY);
        environment.put(PROVIDER_URL, WEBLOGIC_PROVIDER_URL);
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
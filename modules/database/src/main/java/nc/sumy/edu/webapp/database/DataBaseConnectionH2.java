package nc.sumy.edu.webapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectionH2 implements DataBaseConnection {

    private static final String URL = "jdbc:h2:./../../test";
    private static final String USER = "sa";
    private static final String PASS = "";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }
}

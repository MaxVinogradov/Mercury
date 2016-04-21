package nc.sumy.edu.webapp.database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class H2ConnectionTest {

    @Test
    public void getConnection() throws Exception {
        DataBaseConnection dataBaseConnection = new H2Connection();

        try(Connection connection = dataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("SHOW TABLES FROM INFORMATION_SCHEMA;");
            ResultSet result = statement.executeQuery()) {
        }
    }
}
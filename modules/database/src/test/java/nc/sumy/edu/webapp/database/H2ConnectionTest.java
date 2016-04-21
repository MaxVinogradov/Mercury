package nc.sumy.edu.webapp.database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class H2ConnectionTest {

    @Test
    public void getConnection() throws SQLException {
        DataBaseConnection dataBaseConnection = new H2Connection();
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SHOW TABLES FROM INFORMATION_SCHEMA;");
             ResultSet result = statement.executeQuery()) {
            assertTrue(result.next());
        }
    }
}
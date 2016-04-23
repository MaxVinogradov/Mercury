package nc.sumy.edu.webapp.database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class H2ConnectionTest {

    @Test
    public void getConnection() throws SQLException {
        DataBaseConnection dataBaseConnection = new DataBaseConnectionH2();
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT sysdate();");
             ResultSet result = statement.executeQuery()) {
            assertTrue(result.next());
            assertEquals(DateFormat.getDateInstance().format(new Date()),
                    DateFormat.getDateInstance().format(result.getDate(1)));
        }
    }
}
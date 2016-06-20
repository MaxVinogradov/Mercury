package nc.sumy.edu.webapp.database;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DataBaseConnectionH2Test {

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
    @Test
    public void getConnectionAccountsTable() throws SQLException {
        DataBaseConnection dataBaseConnection = new DataBaseConnectionH2();
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("SELECT * FROM PUBLIC.ACCOUNTS;");
             ResultSet result = statement.executeQuery()) {

            assertTrue(result.next());
            assertEquals(1, result.getInt(1));
            assertEquals("vk.com", result.getString(2));
            assertEquals("+380930484670", result.getString(3));
            assertEquals("123456", result.getString(4));
        }
    }
}
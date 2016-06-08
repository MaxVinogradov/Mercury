package nc.sumy.edu.webapp.database;


import nc.sumy.edu.webapp.database.connection.DataBaseConnection;
import nc.sumy.edu.webapp.database.connection.DataBaseConnectionH2;
import nc.sumy.edu.webapp.database.stubs.Account;
import nc.sumy.edu.webapp.database.stubs.Portal;
import nc.sumy.edu.webapp.database.stubs.Post;
import nc.sumy.edu.webapp.database.stubs.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;

public class DBPullImpl implements DBPull{
    private final DataBaseConnection dataBaseConnection =
            new DataBaseConnectionH2();
    private final String SELECT_USER =
            "SELECT * FROM PUBLIC.USERS WHERE LOGIN = ?;";
    private static final String SELECT_PORTAL =
            "SELECT * FROM PUBLIC.PORTALS WHERE USER_ID = ?;";
    private final String SELECT_ACCOUNT =
            "SELECT * FROM PUBLIC.ACCOUNTS WHERE ACCOUNT_ID = ?;";

    @Override
    public User loadUser(String login) {
        User user = new User();
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                user.setUserId(resultSet.getInt("USER_ID"));
                user.setLogin(resultSet.getString("LOGIN"));
                user.setPassword(resultSet.getString("PASSWORD"));
                user.setMail(resultSet.getString("MAIL"));
                Calendar cal = Calendar.getInstance();
                cal.setTime(resultSet.getDate("BIRTHDAY"));
                user.setPublishDate(cal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Collection<Portal> loadPortals(int userId) {
        Collection<Portal> collection = new LinkedList<>();
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_PORTAL)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Portal portal = new Portal();
                    portal.setUserId(resultSet.getInt("USER_ID"));
                    portal.setUserId(resultSet.getInt("ACCOUNT_ID"));
                    collection.add(portal);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return collection;
    }

    @Override
    public Account loadAccount(int accountId) {
        Account account = new Account();
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ACCOUNT)) {
            statement.setInt(1, accountId);
            try (ResultSet resultSet = statement.executeQuery()) {
                account.setAccountId(resultSet.getInt("ACCOUNT_ID"));
                account.setServiceName(resultSet.getString("SERVICE_NAME"));
                account.setLogin(resultSet.getString("LOGIN"));
                account.setPassword(resultSet.getString("PASSWORD"));
                account.setLastToken(resultSet.getString("LAST_TOKEN"));
                account.setRawResponse(resultSet.getString("RAW_RESPONSE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return account;
    }

    @Override
    public Collection<Post> loadPosts(int userId) {
        return null;
    }

}

package nc.sumy.edu.webapp.database;

import nc.sumy.edu.webapp.database.connection.DataBaseConnection;
import nc.sumy.edu.webapp.database.connection.DataBaseConnectionH2;
import nc.sumy.edu.webapp.database.stubs.Account;
import nc.sumy.edu.webapp.database.stubs.Portal;
import nc.sumy.edu.webapp.database.stubs.Post;
import nc.sumy.edu.webapp.database.stubs.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class DBPullImpl implements DBPull{
    private static final Logger LOGGER = LoggerFactory.getLogger(DBPullImpl.class);
    private static final String ERROR_MASSAGE = "When using the database SQLException was happen.";
    private final DataBaseConnection dataBaseConnection =
            new DataBaseConnectionH2();
    private static final String SELECT_USER =
            "SELECT * FROM PUBLIC.USERS WHERE LOGIN = ?;";
    private static final String SELECT_PORTAL =
            "SELECT * FROM PUBLIC.PORTALS WHERE USER_ID = ?;";
    private static final String SELECT_ACCOUNT =
            "SELECT * FROM PUBLIC.ACCOUNTS WHERE ACCOUNT_ID = ?;";
    private static final String SELECT_POSTS =
            "SELECT * FROM PUBLIC.POSTS WHERE USER_ID = ?;";

    @Override
    public User loadUser(String login) {
        User user = new User();
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                user.setUserId(resultSet.getInt("USER_ID"))
                        .setLogin(resultSet.getString("LOGIN"))
                        .setPassword(resultSet.getString("PASSWORD"))
                        .setMail(resultSet.getString("MAIL"))
                        .setPublishDate(resultSet.getDate("BIRTHDAY"));
            }
        } catch (SQLException e) {
            LOGGER.error(ERROR_MASSAGE, e);
        }
        return user;
    }

    @Override
    public Collection<Portal> loadPortals(int userId) {
        Collection<Portal> collection = new ArrayList<>();
        try (Connection connection = dataBaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_PORTAL)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Portal portal = new Portal();
                    portal.setUserId(resultSet.getInt("USER_ID"))
                            .setAccountId(resultSet.getInt("ACCOUNT_ID"));
                    collection.add(portal);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(ERROR_MASSAGE, e);
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
                account.setAccountId(resultSet.getInt("ACCOUNT_ID"))
                        .setServiceName(resultSet.getString("SERVICE_NAME"))
                        .setLogin(resultSet.getString("LOGIN"))
                        .setPassword(resultSet.getString("PASSWORD"))
                        .setLastToken(resultSet.getString("LAST_TOKEN"))
                        .setRawResponse(resultSet.getString("RAW_RESPONSE"));
            }
        } catch (SQLException e) {
            LOGGER.error(ERROR_MASSAGE, e);
        }
        return account;
    }

    @Override
    public Collection<Post> loadPosts(int userId) {
        Collection<Post> collection = new ArrayList<>();
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_POSTS)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Post post = new Post();
                    post.setUserId(resultSet.getInt("USER_ID"))
                            .setPostId(resultSet.getInt("POST_ID"))
                            .setPublishDate(resultSet.getDate("PUBLISH_DATE"))
                            .setTitle(resultSet.getString("TITLE"))
                            .setBody(resultSet.getString("BODY"));
                    collection.add(post);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(ERROR_MASSAGE, e);
        }
        return collection;
    }

}

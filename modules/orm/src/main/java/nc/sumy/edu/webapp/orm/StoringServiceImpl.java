package nc.sumy.edu.webapp.orm;

import nc.sumy.edu.webapp.database.DataBaseConnection;
import nc.sumy.edu.webapp.database.DataBaseConnectionH2;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworkInfo;
import nc.sumy.edu.webapp.orm.domain.Portal;
import nc.sumy.edu.webapp.orm.domain.Post;
import nc.sumy.edu.webapp.orm.domain.User;

import java.sql.*;

public class StoringServiceImpl implements StoringService {
    private final DataBaseConnection dataBaseConnection =
            new DataBaseConnectionH2();
    private static final String INSERT_USER =
            "INSERT INTO PUBLIC.USERS  (LOGIN, PASSWORD, MAIL, BIRTHDAY) VALUES (?, ?, ?, ?);";
    private static final String INSERT_POST =
            "INSERT INTO PUBLIC.POSTS (USER_ID, PUBLISH_DATE, TITLE, BODY) VALUES (?, ?, ?, ?);";
    private static final String INSERT_ACCOUNT =
            "INSERT INTO PUBLIC.ACCOUNTS (SERVICE_NAME, LOGIN, PASSWORD, LAST_TOKEN, RAW_RESPONSE) VALUES (?, ?, ?, ?, ?);";
    private static final String INSERT_PORTAL =
            "INSERT INTO PUBLIC.PORTALS VALUES (?, ?);";
    private static final String UPDATE_USER =
            "UPDATE PUBLIC.USERS " +
                    "SET LOGIN=?, PASSWORD=?, " +
                    "MAIL=?, BIRTHDAY=? " +
                    "WHERE USER_ID=?;";
    private static final String UPDATE_POST =
            "UPDATE PUBLIC.POSTS " +
                    "SET USER_ID=?, PUBLISH_DATE=?, " +
                    "TITLE=?, BODY=? " +
                    "WHERE POST_ID=?;";
    private static final String UPDATE_ACCOUNT =
            "UPDATE PUBLIC.ACCOUNTS " +
                    "SET USER_ID=?, PUBLISH_DATE=?, " +
                    "TITLE=?, BODY=? " +
                    "WHERE POST_ID=?;";
    //TODO: add column PORTAL_ID to PORTALS table and fix query
    private static final String UPDATE_PORTAL =
            "UPDATE PUBLIC.PORTALS " +
                    "SET ACCOUNT_ID=? " +
                    "WHERE USER_ID=?;";

    @Override
    public User addUser(User user) {
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(INSERT_USER)) {
            setParamUser(statement, user).executeUpdate();
            return user.setUserId(getInsertedId(statement));
        } catch (SQLException e) {
            throw new StoringServiceException("Unable to add a new user: " + user.getLogin(), e);
        }
    }

    @Override
    public User updateUser(User user) {
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_USER)) {
            setParamUser(statement, user).setLong(5, user.getUserId());
            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new StoringServiceException("Unable to update a new user: " + user.getLogin(), e);
        }
    }

    private PreparedStatement setParamUser(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getLogin());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getMail());
        statement.setDate(4, new Date(user.getPublishDate().getTime().getTime()));
        return statement;
    }

    @Override
    public Post addPost(Post post) {
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(INSERT_POST)) {
            setParamPost(statement, post).executeUpdate();
            return post.setPostId(getInsertedId(statement));
        } catch (SQLException e) {
            throw new StoringServiceException("Unable to add a new post from user: " + post.getUserId(), e);
        }
    }

    @Override
    public Post updatePost(Post post) {
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_POST)) {
            setParamPost(statement, post).setLong(5, post.getPostId());
            statement.executeUpdate();
            return post;
        } catch (SQLException e) {
            throw new StoringServiceException("Unable to add a update post from user: " + post.getUserId(), e);
        }
    }

    private PreparedStatement setParamPost(PreparedStatement statement, Post post) throws SQLException {
        statement.setLong(1, post.getUserId());
        statement.setDate(2, new Date(post.getPublishDate().getTime().getTime()));
        statement.setString(3, post.getTitle());
        statement.setString(4, post.getBody());
        return statement;
    }

    @Override
    public SocialNetworkInfo addAccount(int userId, SocialNetworkInfo socialNetworkInfo) {
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(INSERT_ACCOUNT)) {
            setParamAccount(statement, socialNetworkInfo).executeUpdate();
            addPortal(new Portal(userId, getInsertedId(statement)));
            return socialNetworkInfo.setAccountId(getInsertedId(statement));
        } catch (SQLException e) {
            throw new StoringServiceException("Unable to add a new socialNetworkInfo: " + socialNetworkInfo.getLogin(), e);
        }
    }

    @Override
    public SocialNetworkInfo updateAccount(SocialNetworkInfo socialNetworkInfo) {
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_ACCOUNT)) {
            setParamAccount(statement, socialNetworkInfo).setLong(5, socialNetworkInfo.getAccountId());
            statement.executeUpdate();
            return socialNetworkInfo;
        } catch (SQLException e) {
            throw new StoringServiceException("Unable to update a new socialNetworkInfo: " + socialNetworkInfo.getLogin(), e);
        }
    }

    private PreparedStatement setParamAccount(PreparedStatement statement, SocialNetworkInfo account) throws SQLException {
        statement.setString(1, account.getServiceName().getDatabaseName());
        statement.setString(2, account.getLogin());
        statement.setString(3, account.getPassword());
        statement.setString(4, account.getLastToken());
        statement.setString(5, account.getAdditionalTokenField());
        return statement;
    }

    @Override
    public Portal addPortal(Portal portal) {
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(INSERT_PORTAL)) {
            setParamPortal(statement, portal).executeUpdate();
            return portal;
        } catch (SQLException e) {
            throw new StoringServiceException("Unable to add a new portal of userId: " + portal.getUserId(), e);
        }
    }

    @Override
    public Portal updatePortal(Portal portal) {
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_PORTAL)) {
            setParamPortal(statement, portal).executeUpdate();
            return portal;
        } catch (SQLException e) {
            throw new StoringServiceException("Unable to update a new portal of userId: " + portal.getUserId(), e);
        }
    }

    private PreparedStatement setParamPortal(PreparedStatement statement, Portal portal) throws SQLException {
        statement.setLong(1, portal.getUserId());
        statement.setLong(2, portal.getAccountId());
        return statement;
    }

    private int getInsertedId(PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.getGeneratedKeys()) {
            return (resultSet.next() ? resultSet.getInt(1) : 0);
        }
    }
}

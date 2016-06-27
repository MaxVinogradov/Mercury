package nc.sumy.edu.webapp.orm;

import nc.sumy.edu.webapp.database.DataBaseConnection;
import nc.sumy.edu.webapp.database.DataBaseConnectionH2;
import nc.sumy.edu.webapp.database.queryloader.QueryLoader;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworkInfo;
import nc.sumy.edu.webapp.orm.domain.Portal;
import nc.sumy.edu.webapp.orm.domain.Post;
import nc.sumy.edu.webapp.orm.domain.User;

import java.sql.*;

public class StoringServiceImpl implements StoringService {
    private final DataBaseConnection dataBaseConnection =
            new DataBaseConnectionH2();
    private static final String INSERT_USER     = new QueryLoader().get("insert_user.sql");
    private static final String INSERT_POST     = new QueryLoader().get("insert_post.sql");
    private static final String INSERT_ACCOUNT  = new QueryLoader().get("insert_account.sql");
    private static final String INSERT_PORTAL   = new QueryLoader().get("insert_portal.sql");

    private static final String UPDATE_USER     = new QueryLoader().get("update_user.sql");
    private static final String UPDATE_POST     = new QueryLoader().get("update_post.sql");
    private static final String UPDATE_ACCOUNT  = new QueryLoader().get("update_account.sql");
    //TODO: add column PORTAL_ID to PORTALS table and fix query
    private static final String UPDATE_PORTAL   = new QueryLoader().get("update_portal.sql");

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
        statement.setDate(4, new Date(user.getBirthDate().getTime().getTime()));
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

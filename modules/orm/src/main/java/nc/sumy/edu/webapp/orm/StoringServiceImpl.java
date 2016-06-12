package nc.sumy.edu.webapp.orm;

import nc.sumy.edu.webapp.database.DataBaseConnection;
import nc.sumy.edu.webapp.database.DataBaseConnectionH2;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworkInfo;
import nc.sumy.edu.webapp.orm.domain.Portal;
import nc.sumy.edu.webapp.orm.domain.Post;
import nc.sumy.edu.webapp.orm.domain.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    @Override
    public User addUser(User user) {
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_USER)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getMail());
            ps.setDate  (4, new Date(user.getPublishDate().getTime().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new StoringServiceException("Unable to add a new user: " + user.getLogin(), e);
        }
        return user;
    }

    @Override
    public Post addPost(Post post) {
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_POST)) {
            ps.setLong  (1, post.getUserId());
            ps.setDate  (2, new Date(post.getPublishDate().getTime().getTime()));
            ps.setString(3, post.getTitle());
            ps.setString(4, post.getBody());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new StoringServiceException("Unable to add a new post from user: " + post.getUserId(), e);
        }
        return post;
    }

    @Override
    public SocialNetworkInfo addAccount(SocialNetworkInfo socialNetworkInfo) {
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_ACCOUNT)) {
            ps.setString(1, socialNetworkInfo.getServiceName().getDatabaseName());
            ps.setString(2, socialNetworkInfo.getLogin());
            ps.setString(3, socialNetworkInfo.getPassword());
            ps.setString(4, socialNetworkInfo.getLastToken());
            ps.setString(5, socialNetworkInfo.getAdditionalTokenField());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new StoringServiceException("Unable to add a new socialNetworkInfo: " + socialNetworkInfo.getLogin(), e);
        }
        return socialNetworkInfo;
    }

    @Override
    public Portal addPortal(Portal portal) {
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_PORTAL)) {
            ps.setLong(1, portal.getUserId());
            ps.setLong(2, portal.getAccountId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new StoringServiceException("Unable to add a new portal of userId: " + portal.getUserId(), e);
        }
        return portal;
    }
}

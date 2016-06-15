package nc.sumy.edu.webapp.orm;

import nc.sumy.edu.webapp.database.DataBaseConnection;
import nc.sumy.edu.webapp.database.DataBaseConnectionH2;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworkInfo;
import nc.sumy.edu.webapp.orm.domain.Portal;
import nc.sumy.edu.webapp.orm.domain.Post;
import nc.sumy.edu.webapp.orm.domain.User;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class LoadingServiceImpl implements LoadingService {
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
    private static final String SELECT_ACCOUNTS_VIA_POSTS =
            "SELECT ACC.ACCOUNT_ID, ACC.SERVICE_NAME, ACC.LOGIN, ACC.PASSWORD, ACC.LAST_TOKEN, ACC.RAW_RESPONSE " +
            "FROM   PUBLIC.ACCOUNTS ACC, PUBLIC.PORTALS PORT " +
            "WHERE  ACC.USER_ID = PORT.USER_ID AND ACC.USER_ID = ?;";

    @Override
    public User loadUser(String login) {
        User user = new User();
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_USER)) {
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                user.setUserId(rs.getInt        ("USER_ID"))
                    .setLogin(rs.getString      ("LOGIN"))
                    .setPassword(rs.getString   ("PASSWORD"))
                    .setMail(rs.getString       ("MAIL"))
                    .setPublishDate(rs.getDate  ("BIRTHDAY"));
            }
        } catch (SQLException e) {
            throw new LoadingServiceException("Unable to load a new user: " + login, e);
        }
        return user;
    }

    @Override
    public Collection<Portal> loadPortals(int userId) {
        Collection<Portal> collection = new ArrayList<>();
        try (Connection conn = dataBaseConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_PORTAL)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next())
                    collection.add(new Portal(rs.getInt("USER_ID"), rs.getInt("ACCOUNT_ID")));
            }
        } catch (SQLException e) {
            throw new LoadingServiceException("Unable to load a portals of user: " + userId, e);
        }
        return collection;
    }

    @Override
    public SocialNetworkInfo loadAccount(int accountId) {
        SocialNetworkInfo socialNetworkInfo = new SocialNetworkInfo();
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ACCOUNT)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                socialNetworkInfo.setAccountId(rs.getInt                ("ACCOUNT_ID"));
                socialNetworkInfo.setServiceName(SocialNetworks
                        .getNetworkType(rs.getString                    ("SERVICE_NAME")));
                socialNetworkInfo.setLogin(rs.getString                 ("LOGIN"));
                socialNetworkInfo.setPassword(rs.getString              ("PASSWORD"));
                socialNetworkInfo.setLastToken(rs.getString             ("LAST_TOKEN"));
                socialNetworkInfo.setAdditionalTokenField(rs.getString  ("RAW_RESPONSE"));
            }
        } catch (SQLException e) {
            throw new LoadingServiceException("Unable to load a socialNetworkInfo with accountId: " + accountId, e);
        }
        return socialNetworkInfo;
    }

    @Override
    public Collection<SocialNetworkInfo> loadAccounts(int userId) {
        Collection<SocialNetworkInfo> collection = new HashSet<>();
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ACCOUNTS_VIA_POSTS)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SocialNetworkInfo socialNetworkInfo = new SocialNetworkInfo();
                    socialNetworkInfo.setAccountId(rs.getInt                ("ACCOUNT_ID"));
                    socialNetworkInfo.setServiceName(SocialNetworks
                            .getNetworkType(rs.getString                    ("SERVICE_NAME")));
                    socialNetworkInfo.setLogin(rs.getString                 ("LOGIN"));
                    socialNetworkInfo.setPassword(rs.getString              ("PASSWORD"));
                    socialNetworkInfo.setLastToken(rs.getString             ("LAST_TOKEN"));
                    socialNetworkInfo.setAdditionalTokenField(rs.getString  ("RAW_RESPONSE"));
                    collection.add(socialNetworkInfo);
                }
            }
        } catch (SQLException e) {
            throw new LoadingServiceException("Unable to load a post of user: " + userId, e);
        }
        return collection;
    }

    @Override
    public Collection<Post> loadPosts(int userId) {
        Collection<Post> collection = new ArrayList<>();
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_POSTS)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Post post = new Post();
                    post.setUserId(rs.getInt            ("USER_ID"))
                            .setPostId(rs.getInt        ("POST_ID"))
                            .setPublishDate(rs.getDate  ("PUBLISH_DATE"))
                            .setTitle(rs.getString      ("TITLE"))
                            .setBody(rs.getString       ("BODY"));
                    collection.add(post);
                }
            }
        } catch (SQLException e) {
            throw new LoadingServiceException("Unable to load a post of user: " + userId, e);
        }
        return collection;
    }

}

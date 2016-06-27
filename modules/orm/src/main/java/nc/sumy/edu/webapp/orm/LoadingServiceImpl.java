package nc.sumy.edu.webapp.orm;

import nc.sumy.edu.webapp.database.DataBaseConnection;
import nc.sumy.edu.webapp.database.DataBaseConnectionCreator;
import nc.sumy.edu.webapp.database.DataBaseConnectionH2;
import nc.sumy.edu.webapp.database.queryloader.QueryLoader;
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
import java.util.LinkedList;

public class LoadingServiceImpl implements LoadingService {
    private final DataBaseConnection dataBaseConnection =
            new DataBaseConnectionCreator().getConection();
    private static final String SELECT_USER     = new QueryLoader().get("select_user.sql");
    private static final String SELECT_PORTAL   = new QueryLoader().get("select_portal.sql");
    private static final String SELECT_ACCOUNT  = new QueryLoader().get("select_account.sql");
    private static final String SELECT_POSTS    = new QueryLoader().get("select_posts.sql");
    private static final String SELECT_ACCOUNTS_VIA_PORTS
                                                = new QueryLoader().get("select_accounts_via_posts.sql");

    @Override
    public User loadUser(String login) {
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_USER)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                User user = new User();
                resultSet.next();
                user.setUserId(resultSet.getInt("USER_ID"))
                        .setLogin(resultSet.getString("LOGIN"))
                        .setPassword(resultSet.getString("PASSWORD"))
                        .setMail(resultSet.getString("MAIL"))
                        .setBirthDate(resultSet.getDate("BIRTHDAY"));
                return user;
            }
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Collection<Portal> loadPortals(int userId) {
        Collection<Portal> collection = new ArrayList<>();
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_PORTAL)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    collection.add(new Portal(resultSet.getInt("USER_ID"), resultSet.getInt("ACCOUNT_ID")));
                }
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
             PreparedStatement statement = conn.prepareStatement(SELECT_ACCOUNT)) {
            statement.setInt(1, accountId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet.next()) {
                    String type = resultSet.getString("SERVICE_NAME");
                    SocialNetworks typeN = SocialNetworks.getNetworkType(type);
                    socialNetworkInfo.setServiceName(typeN);
                    socialNetworkInfo.setLastToken(resultSet.getString("LAST_TOKEN"));
                    socialNetworkInfo.setAdditionalTokenField(resultSet.getString("RAW_RESPONSE"));
                }
            }
        } catch (SQLException e) {
            throw new LoadingServiceException("Unable to load a socialNetworkInfo with accountId: " + accountId, e);
        }
        return socialNetworkInfo;
    }

    public Collection<SocialNetworkInfo> loadAccountsWithTwoMethods(int userId) {
        Collection<Portal> portals = loadPortals(userId);
        Collection<SocialNetworkInfo> infos = new HashSet<>();
        for (Portal portal : portals) {
            SocialNetworkInfo info = loadAccount((int) portal.getAccountId());
            infos.add(info);
        }
        return infos;
    }

    @Override
    public Collection<SocialNetworkInfo> loadAccounts(int userId) {
        Collection<SocialNetworkInfo> collection = new HashSet<>();
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_ACCOUNTS_VIA_PORTS)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    SocialNetworkInfo socialNetworkInfo = new SocialNetworkInfo();
                    socialNetworkInfo.setAccountId(resultSet.getInt("ACCOUNT_ID"));
                    socialNetworkInfo.setServiceName(SocialNetworks
                            .getNetworkType(resultSet.getString("SERVICE_NAME")));
                    socialNetworkInfo.setLogin(resultSet.getString("LOGIN"));
                    socialNetworkInfo.setPassword(resultSet.getString("PASSWORD"));
                    socialNetworkInfo.setLastToken(resultSet.getString("LAST_TOKEN"));
                    socialNetworkInfo.setAdditionalTokenField(resultSet.getString("RAW_RESPONSE"));
                    collection.add(socialNetworkInfo);
                }
            }
        } catch (SQLException e) {
            throw new LoadingServiceException("Unable to load a accounts set of user: " + userId, e);
        }
        return collection;
    }

    @Override
    public Collection<Post> loadPosts(int userId) {
        Collection<Post> collection = new LinkedList<>();
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_POSTS)) {
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
            throw new LoadingServiceException("Unable to load a post of user: " + userId, e);
        }
        return collection;
    }

}

package nc.sumy.edu.webapp.database;

import nc.sumy.edu.webapp.database.connection.DataBaseConnection;
import nc.sumy.edu.webapp.database.connection.DataBaseConnectionH2;
import nc.sumy.edu.webapp.database.domain.Account;
import nc.sumy.edu.webapp.database.domain.Portal;
import nc.sumy.edu.webapp.database.domain.Post;
import nc.sumy.edu.webapp.database.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbPushImpl implements StoringService {
    private static final Logger LOG = LoggerFactory.getLogger(DbPushImpl.class);
    private static final String ERROR_MASSAGE = "When using the database SQLException was happen.";
    private final DataBaseConnection dataBaseConnection =
            new DataBaseConnectionH2();
    //Я игнорил первій столбец во всех табличках, не уверен в том как правильно с ним работать кроме как аккаунтов
    private static final String INSERT_USER =
            "INSERT INTO PUBLIC.USERS VALUES (?, ?, ?, ?);";
    private static final String INSERT_POST =
            "INSERT INTO PUBLIC.POSTS VALUES (?, ?, ?, ?);";
    private static final String INSERT_ACCOUNT =
            "INSERT INTO PUBLIC.ACCOUNTS VALUES (?, ?, ?, ?, ?);";
    private static final String INSERT_PORTAL =
            "INSERT INTO PUBLIC.PORTALS VALUES (?, ?);";

    @Override
    public User addUser(User user) {
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getMail());
            statement.setDate(4, new Date(user.getPublishDate().getTime().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Unable to add a new user: " + user.getLogin(), e);
        }
        return user;
    }

    @Override
    public void addPost(Post post) {
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_POST)) {
            statement.setLong(1, post.getUserId());
            statement.setDate(2, new Date(post.getPublishDate().getTime().getTime()));
            statement.setString(3, post.getTitle());
            statement.setString(4, post.getBody());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(ERROR_MASSAGE, e);
        }
    }

    @Override
    public void addAccount(Account account) {
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_ACCOUNT)) {
            statement.setString(1, account.getServiceName());
            statement.setString(2, account.getLogin());
            statement.setString(3, account.getPassword());
            statement.setString(4, account.getLastToken());
            statement.setString(5, account.getRawResponse());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(ERROR_MASSAGE, e);
        }
    }

    @Override
    public void addPortal(Portal portal) {
        try (Connection connection = dataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_PORTAL)) {
            statement.setLong(1, portal.getUserId());
            statement.setLong(2, portal.getAccountId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(ERROR_MASSAGE, e);
        }
    }
}

package nc.sumy.edu.webapp.orm;

import nc.sumy.edu.webapp.database.DataBaseConnection;
import nc.sumy.edu.webapp.database.DataBaseConnectionH2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemovingServiceImpl  implements RemovingService{
    private final DataBaseConnection dataBaseConnection =
            new DataBaseConnectionH2();
    private static final String REMOVE_USER =
            "DELETE PUBLIC.USER WHERE USER_ID = ?;";
    private static final String REMOVE_POST =
            "DELETE PUBLIC.POST WHERE POST_ID = ?;";
    private static final String REMOVE_ACCOUNT =
            "DELETE PUBLIC.ACCOUNT WHERE ACCOUNT_ID = ?;";
    private static final String REMOVE_PORTAL =
            "DELETE PUBLIC.PORTAL WHERE USER_ID = ? AND ACCOUNT_ID = ?;";

    @Override
    public void removeUser(int userId) {
        makeRemoving(userId, REMOVE_USER, "Unable to remove user: " + userId);
    }

    @Override
    public void removePost(int postId) {
        makeRemoving(postId, REMOVE_POST, "Unable to remove post: " + postId);
    }

    @Override
    public void removeSocialNetworkInfo(int accountId) {
        makeRemoving(accountId, REMOVE_ACCOUNT, "Unable to remove account: " + accountId);
    }

    @Override
    public void removeUsersAccount(int account, int user) {
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(REMOVE_PORTAL)) {
            statement.setInt(1, user);
            statement.setInt(2, account);
            statement.execute();
        } catch (SQLException e) {
            throw new RemovingServiceException(
                    "Unable to remove account " + account + " of user " + user, e);
        }
    }

    private void makeRemoving(int id, String query, String exceptionText) {
        try (Connection conn = dataBaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RemovingServiceException(exceptionText, e);
        }
    }
}

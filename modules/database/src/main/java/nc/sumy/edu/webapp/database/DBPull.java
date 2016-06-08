package nc.sumy.edu.webapp.database;

import nc.sumy.edu.webapp.database.stubs.Account;
import nc.sumy.edu.webapp.database.stubs.Portal;
import nc.sumy.edu.webapp.database.stubs.Post;
import nc.sumy.edu.webapp.database.stubs.User;

import java.util.Collection;

public interface DBPull {

    User loadUser(String login);

    Collection<Portal> loadPortals(int userId);

    Account loadAccount(int accountId);

    Collection<Post> loadPosts(int userId);

}

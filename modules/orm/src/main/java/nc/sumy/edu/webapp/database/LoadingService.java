package nc.sumy.edu.webapp.database;

import nc.sumy.edu.webapp.database.domain.Account;
import nc.sumy.edu.webapp.database.domain.Portal;
import nc.sumy.edu.webapp.database.domain.Post;
import nc.sumy.edu.webapp.database.domain.User;

import java.util.Collection;

public interface LoadingService {

    User loadUser(String login);

    Collection<Portal> loadPortals(int userId);

    Account loadAccount(int accountId);

    Collection<Post> loadPosts(int userId);

}

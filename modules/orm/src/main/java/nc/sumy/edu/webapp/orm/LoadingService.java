package nc.sumy.edu.webapp.orm;

import nc.sumy.edu.webcontainer.common.integration.Account;
import nc.sumy.edu.webapp.orm.domain.Portal;
import nc.sumy.edu.webapp.orm.domain.Post;
import nc.sumy.edu.webapp.orm.domain.User;

import java.util.Collection;

public interface LoadingService {

    User loadUser(String login);

    Collection<Portal> loadPortals(int userId);

    Account loadAccount(int accountId);

    Collection<Post> loadPosts(int userId);

}

package nc.sumy.edu.webapp.database;

import nc.sumy.edu.webapp.database.stubs.Account;
import nc.sumy.edu.webapp.database.stubs.Portal;
import nc.sumy.edu.webapp.database.stubs.Post;
import nc.sumy.edu.webapp.database.stubs.User;

public interface DbPush {

    void addUser(User user);

    void addPost(Post post);

    void addAccount(Account account);

    void addPortal(Portal portal);

}

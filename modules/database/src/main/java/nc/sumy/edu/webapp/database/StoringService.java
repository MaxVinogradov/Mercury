package nc.sumy.edu.webapp.database;

import nc.sumy.edu.webapp.database.domain.Account;
import nc.sumy.edu.webapp.database.domain.Portal;
import nc.sumy.edu.webapp.database.domain.Post;
import nc.sumy.edu.webapp.database.domain.User;

public interface StoringService {

    /**
     * @param  user without PK, just POJO
     * @return user with primary key or id
    */
    User addUser(User user);

    void addPost(Post post);

    void addAccount(Account account);

    void addPortal(Portal portal);

}

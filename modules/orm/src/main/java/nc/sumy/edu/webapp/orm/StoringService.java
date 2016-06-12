package nc.sumy.edu.webapp.orm;

import nc.sumy.edu.webcontainer.common.integration.Account;
import nc.sumy.edu.webapp.orm.domain.Portal;
import nc.sumy.edu.webapp.orm.domain.Post;
import nc.sumy.edu.webapp.orm.domain.User;

public interface StoringService {

    /**
     * @param  user without PK, just POJO
     * @return user with primary key or id
    */
    User addUser(User user);

    Post addPost(Post post);

    Account addAccount(Account account);

    Portal addPortal(Portal portal);

}

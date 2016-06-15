package nc.sumy.edu.webapp.orm;

public interface RemovingService {

    void removeUser(int userId);

    void removePost(int postId);

    void removeUsersAccount(int service, int user);

    void removeSocialNetworkInfo(int accountId);

}

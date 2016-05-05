package nc.sumy.edu.webapp;

public interface Integration {



    boolean publishPost(String message);

    //is it correct to move this method to interface?
    //as it strictly defines social networks we work with
    void connect(String vkId, String fbId, String twitterId);
}

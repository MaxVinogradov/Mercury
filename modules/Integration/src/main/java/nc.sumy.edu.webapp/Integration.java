package nc.sumy.edu.webapp;

import java.util.Set;

public interface Integration {



    boolean publishPost(String message);


    void connect(Set<SocialNetworkInfo> networkInfoSet);
}

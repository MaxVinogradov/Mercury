package nc.sumy.edu.webapp;

import java.util.EnumMap;
import java.util.Map;
import java.util.TreeMap;

public class IntegrationImpl implements Integration {
    private Map<SocialNetwork,SocialNetworkIntegration> connected;

    public IntegrationImpl(String vkId, String fbId, String twitterId) {
        connected = new TreeMap<SocialNetwork,SocialNetworkIntegration>();
        this.connect(vkId, fbId, twitterId);
    }

    public void connectToVk(String id) {
        SocialNetworkIntegration vk = new VkIntegration(id);
        if(vk.connect()) {
            connected.put(SocialNetwork.VK, vk);
        }
    }

    public void connectToFb(String id) {
        SocialNetworkIntegration fb = new FacebookIntegration(id);
        if(fb.connect()) {
            connected.put(SocialNetwork.FACEBOOK, fb);
        }
    }

    public void connectToTwitter(String id) {
        SocialNetworkIntegration twitter = new TwitterIntegration(id);
        if(twitter.connect()) {
            connected.put(SocialNetwork.TWITTER, twitter);
        }
    }



    @Override
    public boolean publishPost(String message) {
        boolean flag = true;
        for (Map.Entry<SocialNetwork,SocialNetworkIntegration> entry: connected.entrySet()){
            flag = flag && entry.getValue().publishPost(message);
        }
        return flag;
    }

    @Override
    public void connect(String vkId, String fbId, String twitterId) {
        this.connectToVk(vkId);
        this.connectToFb(fbId);
        this.connectToTwitter(twitterId);
    }
}

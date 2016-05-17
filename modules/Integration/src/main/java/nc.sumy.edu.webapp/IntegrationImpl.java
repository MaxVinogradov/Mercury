package nc.sumy.edu.webapp;

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;

public class IntegrationImpl implements Integration {
    private Map<SocialNetworks,SocialNetworkIntegration> connected;

    public IntegrationImpl(Set<SocialNetworkInfo> networkInfoSet) {
        connected = new TreeMap<SocialNetworks,SocialNetworkIntegration>();
        this.connect();
    }

    public void connectToVk(String id) {
        SocialNetworkIntegration vk = new VkIntegration(id);
        if(vk.connect()) {
            connected.put(SocialNetworks.VK, vk);
        }
    }

    public void connectToFb(String id) {
        SocialNetworkIntegration fb = new FacebookIntegration(id);
        if(fb.connect()) {
            connected.put(SocialNetworks.FACEBOOK, fb);
        }
    }

    public void connectToTwitter(String id) {
        SocialNetworkIntegration twitter = new TwitterIntegration(id);
        if(twitter.connect()) {
            connected.put(SocialNetworks.TWITTER, twitter);
        }
    }



    @Override
    public boolean publishPost(String message) {
        boolean flag = true;
        for (Map.Entry<SocialNetworks,SocialNetworkIntegration> entry: connected.entrySet()){
            flag = flag && entry.getValue().publishPost(message);
        }
        return flag;
    }

    @java.lang.Override
    public void connect(Set<SocialNetworkInfo> networkInfoSet) {
        for (SocialNetworkInfo networkInfo: networkInfoSet) {
            SocialNetworkIntegration networkIntegration = null;
            switch(networkInfo.getNetworkType()) {
                case VK:
                    networkIntegration = new VkIntegration(networkInfo.getNetworkId());
                    break;
                case FACEBOOK:
                    networkIntegration = new FacebookIntegration(networkInfo.getNetworkId());
                    break;
                case TWITTER:
                    networkIntegration = new TwitterIntegration(networkInfo.getNetworkId());
                    break;
            }
            if(networkIntegration != null && networkIntegration.connect()) {
                connected.put(networkInfo.getNetworkType(), networkIntegration);
            }
        }
    }


}

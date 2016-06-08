package nc.sumy.edu.webapp.integration;

import java.util.Map;
import java.util.TreeMap;
import java.util.Set;

public class IntegrationImpl implements Integration {
    Set<SocialNetworkInfo> networkInfoSet;

    @Override
    public boolean publishPost(String message) {
        return false;
    }

    @Override
    public SocialNetworkInfo processCode(SocialNetworks type, String code) {
        return null;
    }

    @Override
    public String getAuthorisationUrlForNetwork(SocialNetworks type) {
        String authUrl = null;
        switch(type) {
            case VK:
                authUrl = new VkIntegration().getAuthorisationUrl();
                break;
            case FACEBOOK:
                authUrl = new FacebookIntegration().getAuthorisationUrl();
                break;
            case TWITTER:
                authUrl = new TwitterIntegration().getAuthorisationUrl();
                break;
        }
        return authUrl;
    }

    @Override
    public void connect(Set<SocialNetworkInfo> networkInfoSet) {

    }
}

package nc.sumy.edu.webapp.integration;

import com.github.scribejava.core.model.OAuth2AccessToken;

import java.util.Set;

public class IntegrationImpl implements Integration {
    private Set<SocialNetworkInfo> networkInfoSet;


    @Override
    public boolean publishPost(String message) {
        if(networkInfoSet == null) return false;
        boolean flag = true;
        for (SocialNetworkInfo info : networkInfoSet) {
            String tokenString = info.getToken();
            String rawResponse = info.getRawResponse();
            if (rawResponse == null || tokenString == null) {
                flag = false;
                continue;
            }
            SocialNetworks type = info.getNetworkType();
            OAuth2AccessToken token = new OAuth2AccessToken(tokenString, rawResponse);
            boolean current;
            switch(type) {
                case VK:
                    current = new VkIntegration().post(token, message);
                    break;
                case FACEBOOK:
                    current = new FacebookIntegration().post(token, message);
                    break;
                case TWITTER:
                    current = new TwitterIntegration().post(token, message);
                    break;
                default:
                    current = false;
            }
            flag = flag && current;
        }
        return flag;
    }

    @Override
    public SocialNetworkInfo processCode(SocialNetworks type, String code) {
        SocialNetworkInfo info;
        switch(type) {
            case VK:
                info = new VkIntegration().getAccessTokenByCode(code);
                break;
            case FACEBOOK:
                info = new FacebookIntegration().getAccessTokenByCode(code);
                break;
            case TWITTER:
                info = new TwitterIntegration().getAccessTokenByCode(code);
                break;
            default:
                info = null;
        }
        return info;
    }


    @Override
    public String getAuthorisationUrlForNetwork(SocialNetworks type) {
        String authUrl;
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
            default:
                authUrl = null;
        }
        return authUrl;
    }

    @Override
    public void connect(Set<SocialNetworkInfo> networkInfoSet) {
        this.networkInfoSet = networkInfoSet;
    }
}

package nc.sumy.edu.webapp.integration;

import com.github.scribejava.core.model.OAuth2AccessToken;

import java.util.Set;

import static nc.sumy.edu.webapp.integration.SocialNetworks.TWITTER;

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
                    break;
            }
            flag = flag && current;
        }
        return flag;
    }

    @Override
    public SocialNetworkInfo processCodeForOAuth2(SocialNetworks type, String code) {
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
                break;
        }
        return info;
    }

    @Override
    public SocialNetworkInfo processCodeForOAuth1(SocialNetworks type, String requestCode, String code) {
        SocialNetworkInfo info = null;
        if(type == TWITTER) {
            info = new TwitterIntegration().getAccessTokenByCode(requestCode, code);
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
                break;
        }
        return authUrl;
    }

    @Override
    public void setConnectedNetworks(Set<SocialNetworkInfo> networkInfoSet) {
        this.networkInfoSet = networkInfoSet;
    }
}

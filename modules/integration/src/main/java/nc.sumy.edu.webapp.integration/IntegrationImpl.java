package nc.sumy.edu.webapp.integration;

import com.github.scribejava.core.model.OAuth2AccessToken;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static nc.sumy.edu.webapp.integration.SocialNetworks.TWITTER;

public class IntegrationImpl implements Integration {

    private Set<SocialNetworkInfo> networkInfoSet;
    private Set<SocialNetworkIntegration> integrations = new HashSet<>();
    private Map<SocialNetworks, SocialNetworkIntegration> integrationMapping = new HashMap<>();

    @Override
    public ResultOfPostSubmit submitPost(String message) {
        if(networkInfoSet == null) return false;
        Set<PostSubmitResult> results = new HashSet<>();
        for (SocialNetworkInfo info : networkInfoSet) {
            String tokenString = info.getToken();
            String rawResponse = info.getRawResponse();
            if (rawResponse == null || tokenString == null) {
                continue;
            }
            SocialNetworks type = info.getNetworkType();
            OAuth2AccessToken token = new OAuth2AccessToken(tokenString, rawResponse);


            for(SocialNetworkIntegration integration: integrations)
                results.add( integration.post(token, message) );
            }
        }
        return ;
    }

    @Override
    public SocialNetworkInfo processCodeForOAuth2(SocialNetworks type, String code) {
        return integrationMapping.get(type).getAccessTokenByCode(code);
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

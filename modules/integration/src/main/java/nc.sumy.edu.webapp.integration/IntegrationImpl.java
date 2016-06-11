package nc.sumy.edu.webapp.integration;

import com.github.scribejava.core.model.OAuth2AccessToken;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static nc.sumy.edu.webapp.integration.SocialNetworks.FACEBOOK;
import static nc.sumy.edu.webapp.integration.SocialNetworks.TWITTER;
import static nc.sumy.edu.webapp.integration.SocialNetworks.VK;

public class IntegrationImpl implements Integration {

    private Set<SocialNetworkInfo> networkInfoSet;
    private Map<SocialNetworks, SocialNetworkIntegration> integrationMapping =
            new HashMap<SocialNetworks, SocialNetworkIntegration>() {{
                put(VK, new VkIntegration());
                put(FACEBOOK, new FacebookIntegration());
                put(TWITTER, new TwitterIntegration());
            }};

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
        SocialNetworkIntegration sni = integrationMapping.get(type);
        return (sni instanceof OAuth2Integration) ?
                ((OAuth2Integration) sni).getAccessTokenByCode(code) :
                null;
    }

    @Override
    public SocialNetworkInfo processCodeForOAuth1(SocialNetworks type, String requestCode, String code) {
        SocialNetworkIntegration sni = integrationMapping.get(type);
        return (sni instanceof OAuth1Integration) ?
                ((OAuth1Integration) sni).getAccessTokenByCode(requestCode, code) :
                null;
    }


    @Override
    public String getAuthorisationUrlForNetwork(SocialNetworks type) {
        return integrationMapping.get(type).getAuthorisationUrl();
    }

    @Override
    public void setConnectedNetworks(Set<SocialNetworkInfo> networkInfoSet) {
        this.networkInfoSet = networkInfoSet;
    }
}

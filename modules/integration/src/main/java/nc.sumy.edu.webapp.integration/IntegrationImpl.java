package nc.sumy.edu.webapp.integration;

import nc.sumy.edu.webapp.integration.common.ResultOfPostSubmit;
import nc.sumy.edu.webapp.integration.common.SocialNetworkInfo;
import nc.sumy.edu.webapp.integration.common.SocialNetworks;
import nc.sumy.edu.webapp.integration.exceptions.IntegrationNotFoundException;
import nc.sumy.edu.webapp.integration.exceptions.IntegrationTypeMismatchException;
import nc.sumy.edu.webapp.integration.oauth1.OAuth1Integration;
import nc.sumy.edu.webapp.integration.oauth1.TwitterIntegration;
import nc.sumy.edu.webapp.integration.oauth2.FacebookIntegration;
import nc.sumy.edu.webapp.integration.oauth2.OAuth2Integration;
import nc.sumy.edu.webapp.integration.oauth2.VkIntegration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static nc.sumy.edu.webapp.integration.common.SocialNetworks.FACEBOOK;
import static nc.sumy.edu.webapp.integration.common.SocialNetworks.TWITTER;
import static nc.sumy.edu.webapp.integration.common.SocialNetworks.VK;

public class IntegrationImpl implements Integration {

    private Map<SocialNetworks, SocialNetworkIntegration> integrationMapping =
            new HashMap<SocialNetworks, SocialNetworkIntegration>() {{
                put(VK, new VkIntegration());
                put(FACEBOOK, new FacebookIntegration());
                put(TWITTER, new TwitterIntegration());
            }};

    @Override
    public Set<ResultOfPostSubmit> submitPost(Set<SocialNetworkInfo> networkInfoSet, String message) {
        if(networkInfoSet == null) return null;
        Set<ResultOfPostSubmit> results = new HashSet<>();
        for (SocialNetworkInfo info : networkInfoSet) {
            String tokenString = info.getToken();
            String rawResponse = info.getRawResponse();
            SocialNetworks type = info.getNetworkType();
            SocialNetworkIntegration sni = integrationMapping.get(type);
            if (rawResponse == null || tokenString == null || sni == null)
                results.add(new ResultOfPostSubmit(info, false));
            else
                results.add(new ResultOfPostSubmit(info, sni.post(info, message)));
        }
        return results;
    }

    @Override
    public SocialNetworkInfo processCodeForOAuth2(SocialNetworks type, String code) {
        SocialNetworkIntegration sni = integrationMapping.get(type);
        if (sni == null) throw new IntegrationNotFoundException(type);
        if (sni instanceof OAuth2Integration)
            return ((OAuth2Integration) sni).getAccessTokenByCode(code);
        else
            throw new IntegrationTypeMismatchException(type, "OAuth2");
    }

    @Override
    public SocialNetworkInfo processCodeForOAuth1(SocialNetworks type, String requestCode, String code) {
        SocialNetworkIntegration sni = integrationMapping.get(type);
        if (sni == null) throw new IntegrationNotFoundException(type);
        if (sni instanceof OAuth1Integration)
            return ((OAuth1Integration) sni).getAccessTokenByCode(requestCode, code);
        else
            throw new IntegrationTypeMismatchException(type, "OAuth1");
    }


    @Override
    public String getAuthorisationUrlForNetwork(SocialNetworks type) {
        SocialNetworkIntegration sni = integrationMapping.get(type);
        if(sni == null) throw new IntegrationNotFoundException(type);
        return sni.getAuthorisationUrl();
    }

}

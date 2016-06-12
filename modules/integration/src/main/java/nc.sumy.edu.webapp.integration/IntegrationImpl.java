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

import java.util.*;
import java.util.stream.Collectors;

import static nc.sumy.edu.webapp.integration.common.SocialNetworks.FACEBOOK;
import static nc.sumy.edu.webapp.integration.common.SocialNetworks.TWITTER;
import static nc.sumy.edu.webapp.integration.common.SocialNetworks.VK;

public class IntegrationImpl implements Integration {

    private Map<SocialNetworks, SocialNetworkIntegration> integrationMapping;
    private static final Map<SocialNetworks, SocialNetworkIntegration> DEFAULT_MAPPING;

    static {
        DEFAULT_MAPPING = getDefaultMapping();
    }

    public IntegrationImpl(Map<SocialNetworks, SocialNetworkIntegration> integrationMapping) {
        this.integrationMapping = integrationMapping;
    }

    public IntegrationImpl() {
        this.integrationMapping = DEFAULT_MAPPING;
    }

    private static Map<SocialNetworks, SocialNetworkIntegration> getDefaultMapping() {
        Map<SocialNetworks, SocialNetworkIntegration> map = new HashMap<>();
        map.put(VK, new VkIntegration());
        map.put(FACEBOOK, new FacebookIntegration());
        map.put(TWITTER, new TwitterIntegration());
        return map;
    }

    @Override
    public Set<ResultOfPostSubmit> submitPost(Set<SocialNetworkInfo> networkInfoSet, String message) {
        if(checkInfoSet(networkInfoSet)) return Collections.emptySet();
        Set<ResultOfPostSubmit> results = new HashSet<>();
        if(checkMessage(message))
            results.addAll(networkInfoSet.stream()
                    .map(info -> new ResultOfPostSubmit(info, false)).collect(Collectors.toList()));
        else {
            for (SocialNetworkInfo info : networkInfoSet) {
                String tokenString = info.getToken();
                String rawResponse = info.getAdditionalTokenField();
                SocialNetworks type = info.getNetworkType();
                SocialNetworkIntegration sni = integrationMapping.get(type);
                if (checkNetworkInfo(rawResponse, tokenString, sni))
                    results.add(new ResultOfPostSubmit(info, false));
                else
                    results.add(new ResultOfPostSubmit(info, sni.post(info, message)));
            }
        }
        return results;
    }

    private boolean checkNetworkInfo(String rawResponse, String tokenString, SocialNetworkIntegration sni) {
        return rawResponse == null || rawResponse.length() == 0 || tokenString == null
                || tokenString.length() == 0|| sni == null;
    }

    private boolean checkInfoSet(Set<SocialNetworkInfo> networkInfoSet) {
        return networkInfoSet == null || networkInfoSet.isEmpty();
    }

    private boolean checkMessage(String message){
        return message == null || message.length() == 0;
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

    public void setIntegrationMapping(Map<SocialNetworks, SocialNetworkIntegration> integrationMapping) {
        this.integrationMapping = integrationMapping;
    }
}

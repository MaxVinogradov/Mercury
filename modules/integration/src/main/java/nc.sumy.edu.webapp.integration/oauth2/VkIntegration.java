package nc.sumy.edu.webapp.integration.oauth2;

import com.github.scribejava.apis.VkontakteApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworkInfo;
import nc.sumy.edu.webcontainer.common.integration.SocialNetworks;

public class VkIntegration implements OAuth2Integration {
    private static final String APP_ID = "5454568";
    private static final String APP_SECRET = "DUv5bShooOTH7Vzsyqpe";
    private final OAuth20Service service = new ServiceBuilder()
            .apiKey(APP_ID)
            .apiSecret(APP_SECRET)
            .scope("wall,offline,photos")
            .callback("http://oauth.vk.com/blank.html")
            .build(VkontakteApi.instance());
    private static final String WALL_POST_URL = "https://api.vk.com/method/wall.post";

    @Override
    public String getAuthorisationUrl() {
        return service.getAuthorizationUrl() + "&revoke=1";
    }

    @Override
    public SocialNetworkInfo getAccessTokenByCode(String code) {
        OAuth2AccessToken accessToken = service.getAccessToken(code);
        SocialNetworkInfo info = new SocialNetworkInfo();
        info.setServiceName(SocialNetworks.VK);
        info.setLastToken(accessToken.getAccessToken());
        info.setAdditionalTokenField(accessToken.getRawResponse());
        return info;
    }

    @Override
    public boolean post(SocialNetworkInfo info, String message) {
        OAuth2AccessToken accessToken = new OAuth2AccessToken(info.getLastToken(), info.getAdditionalTokenField());
        OAuthRequest request = new OAuthRequest(Verb.GET, WALL_POST_URL, service);
        service.signRequest(accessToken, request);
        Response response = request.send();
        return !response.getBody().contains("error");
    }
}

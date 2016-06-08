package nc.sumy.edu.webapp.integration;


import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class FacebookIntegration implements SocialNetworkIntegration{
    private static final String APP_ID = "220252605025084";
    private static final String APP_SECRET = "5df062aeebae2a5c332e534133679a02";
    private final OAuth20Service service = new ServiceBuilder()
            .apiKey(APP_ID)
            .apiSecret(APP_SECRET)
            .scope("publish_actions")
            .callback("http://www.example.com/oauth_callback/")
            .build(FacebookApi.instance());
    private static final String WALL_POST_URL = "https://graph.facebook.com/v2.6/me/feed";

    @Override
    public String getAuthorisationUrl() {
        return service.getAuthorizationUrl();
    }

    @Override
    public SocialNetworkInfo getAccessTokenByCode(String code) {
        OAuth2AccessToken accessToken = service.getAccessToken(code);
        SocialNetworkInfo info = new SocialNetworkInfo();
        info.setNetworkType(SocialNetworks.FACEBOOK);
        info.setToken(accessToken.getAccessToken());
        info.setRawResponse(accessToken.getRawResponse());
        return info;
    }

    @Override
    public boolean post(SocialNetworkInfo info, String message) {
        return false;
    }

    @Override
    public boolean post(OAuth2AccessToken token, String message) {
        OAuthRequest request = new OAuthRequest(Verb.POST, WALL_POST_URL, service);
        service.signRequest(token, request);
        request.addParameter("message", message);
        Response response = request.send();
        return !response.getBody().contains("error");
    }


}

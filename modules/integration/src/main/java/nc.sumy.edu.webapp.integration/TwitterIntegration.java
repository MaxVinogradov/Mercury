package nc.sumy.edu.webapp.integration;


import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth10aService;

public class TwitterIntegration implements SocialNetworkIntegration{
    private static final String APP_ID = "6DCIfit5FkI6bbj0I9OUDb6IO";
    private static final String APP_SECRET = "0Wd0RnccP4GVllbjhK9nREnTfPmx7TSGyGdYoYHUsNIhMNjggt";
    private final OAuth10aService service = new ServiceBuilder()
            .apiKey(APP_ID)
            .apiSecret(APP_SECRET)
            .callback("http://www.example.com/oauth_callback/")
            .build(TwitterApi.instance());

    @Override
    public String getAuthorisationUrl() {
        return service.getAuthorizationUrl(service.getRequestToken());
    }

    @Override
    public SocialNetworkInfo getAccessTokenByCode(String code) {
        return null;
    }

    @Override
    public boolean post(SocialNetworkInfo info, String message) {
        return false;
    }

    @Override
    public boolean post(OAuth2AccessToken token, String message) {
        return false;
    }
}

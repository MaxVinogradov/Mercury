package nc.sumy.edu.webapp.integration.oauth1;


import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import nc.sumy.edu.webapp.integration.common.SocialNetworkInfo;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterIntegration implements OAuth1Integration{
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
    public boolean post(SocialNetworkInfo info, String message) {
        ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(APP_ID)
                .setOAuthConsumerSecret(APP_SECRET)
                .setOAuthAccessToken(info.getToken())
                .setOAuthAccessTokenSecret(info.getAdditionalTokenField());
        Twitter twitter = new TwitterFactory(configurationBuilder.build()).getInstance();
        try {
            twitter.updateStatus(message);
        } catch (TwitterException e) {
            return false;
        }
        return true;
    }

    public SocialNetworkInfo getAccessTokenByCode(String requestCode, String code) {
        OAuth1AccessToken token =  service.getAccessToken(new OAuth1RequestToken(requestCode, ""), code);
        SocialNetworkInfo info = new SocialNetworkInfo();
        info.setToken(token.getToken());
        info.setAdditionalTokenField(token.getTokenSecret());
        return info;
    }
}

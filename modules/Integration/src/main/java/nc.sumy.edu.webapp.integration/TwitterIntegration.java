package nc.sumy.edu.webapp.integration;


public class TwitterIntegration implements SocialNetworkIntegration{
    private String id;

    public TwitterIntegration(String id) {
        this.id = id;
    }

    @Override
    public String authorization() {
        return null;
    }

    @Override
    public String getTokenFromDatabase() {
        return null;
    }

    @Override
    public boolean checkToken(String token) {
        return false;
    }

    @Override
    public boolean post(String token, String message) {
        return false;
    }
}

package nc.sumy.edu.webapp;


public class FacebookIntegration implements SocialNetworkIntegration{
    private String id;

    public FacebookIntegration(String id) {
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

package nc.sumy.edu.webapp;

public class VkIntegration implements SocialNetworkIntegration {
    private String id;

    public VkIntegration(String id) {
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

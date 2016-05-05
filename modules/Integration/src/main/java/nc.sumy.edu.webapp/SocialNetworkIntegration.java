package nc.sumy.edu.webapp;


public interface SocialNetworkIntegration {
    default boolean connect() {
        return (this.authorization() != null);
    }

    String authorization();

    String getTokenFromDatabase();

    boolean checkToken(String token);

    default String getAccessToken() {
        String token = getTokenFromDatabase();
        if(token == null || !this.checkToken(token)) {
            token = this.authorization();
        }
        return token;
    }

    boolean post(String token, String message);

    default boolean publishPost(String message) {
        String token = this.getAccessToken();
        return (token != null && post(token, message));
    }
}

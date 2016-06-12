package nc.sumy.edu.webcontainer.common.integration;

public enum SocialNetworks {
    VK("vk.com"),
    FACEBOOK("facebook.com"),
    TWITTER("twitter.com");

    private String databaseName;

    SocialNetworks(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public static SocialNetworks getNetworkType(String name) {
        for (SocialNetworks network: SocialNetworks.values()) {
            if (network.databaseName.equals(name)) {
                return network;
            }
        }
        return null;
    }
}

package nc.sumy.edu.webapp;

public class SocialNetworkInfo {
    private String networkId;
    private SocialNetworks networkType;

    public String getNetworkId() {
        return networkId;
    }

    public void setNetworkId(String networkId) {
        this.networkId = networkId;
    }

    public SocialNetworks getNetworkType() {
        return networkType;
    }

    public void setNetworkType(SocialNetworks networkType) {
        this.networkType = networkType;
    }
}

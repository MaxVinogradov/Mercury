package nc.sumy.edu.webapp.integration.common;

public class SocialNetworkInfo {
    private String networkId;
    private SocialNetworks networkType;
    private String token;
    private String rawResponse;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRawResponse() {
        return rawResponse;
    }

    public void setRawResponse(String rawResponse) {
        this.rawResponse = rawResponse;
    }
}

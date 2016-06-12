package nc.sumy.edu.webcontainer.common.integration;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SocialNetworkInfo {
    private String networkId;
    private SocialNetworks networkType;
    private String token;
    private String additionalTokenField;

    public SocialNetworkInfo(String networkId, SocialNetworks networkType, String token, String rawResponse) {
        this.networkId = networkId;
        this.networkType = networkType;
        this.token = token;
        this.additionalTokenField = rawResponse;
    }

    public SocialNetworkInfo() {
        super();
    }

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

    public String getAdditionalTokenField() {
        return additionalTokenField;
    }

    public void setAdditionalTokenField(String additionalTokenField) {
        this.additionalTokenField = additionalTokenField;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        SocialNetworkInfo that = (SocialNetworkInfo) obj;
        return this.networkId.equals(that.getNetworkId()) &&
                this.additionalTokenField.equals(that.getAdditionalTokenField()) &&
                this.token.equals(that.getToken()) &&
                this.networkType.equals(that.getNetworkType());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(15, 39)
                .append(networkId)
                .append(networkType)
                .append(token)
                .append(additionalTokenField)
                .toHashCode();
    }
}

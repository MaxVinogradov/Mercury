package nc.sumy.edu.webcontainer.common.integration;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SocialNetworkInfo {
    private String accountId;
    private SocialNetworks serviceName;
    private String lastToken;
    private String additionalTokenField;

    public SocialNetworkInfo(String accountId, SocialNetworks serviceName, String lastToken, String rawResponse) {
        this.accountId = accountId;
        this.serviceName = serviceName;
        this.lastToken = lastToken;
        this.additionalTokenField = rawResponse;
    }

    public SocialNetworkInfo() {
        super();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public SocialNetworks getServiceName() {
        return serviceName;
    }

    public void setServiceName(SocialNetworks serviceName) {
        this.serviceName = serviceName;
    }

    public String getLastToken() {
        return lastToken;
    }

    public void setLastToken(String lastToken) {
        this.lastToken = lastToken;
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
        return this.accountId.equals(that.getAccountId()) &&
                this.additionalTokenField.equals(that.getAdditionalTokenField()) &&
                this.lastToken.equals(that.getLastToken()) &&
                this.serviceName.equals(that.getServiceName());
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(15, 39)
                .append(accountId)
                .append(serviceName)
                .append(lastToken)
                .append(additionalTokenField)
                .toHashCode();
    }
}

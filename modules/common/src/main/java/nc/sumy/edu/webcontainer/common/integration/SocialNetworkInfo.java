package nc.sumy.edu.webcontainer.common.integration;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import static java.util.Objects.isNull;

public class SocialNetworkInfo {
    private int     accountId;
    private SocialNetworks serviceName;
    private String  login;
    private String  password; //delete from structure: from database, from orm.
    private String  lastToken;
    private String  additionalTokenField;

    public SocialNetworkInfo() {}

    public SocialNetworkInfo(int accountId, SocialNetworks serviceName, String lastToken, String additionalTokenField) {
        this.accountId = accountId;
        this.serviceName = serviceName;
        this.lastToken = lastToken;
        this.additionalTokenField = additionalTokenField;
    }

    public SocialNetworkInfo(int accountId, SocialNetworks serviceName, String login,
                             String password, String lastToken, String additionalTokenField) {
        this.accountId = accountId;
        this.serviceName = serviceName;
        this.login = login;
        this.password = password;
        this.lastToken = lastToken;
        this.additionalTokenField = additionalTokenField;
    }

    public int getAccountId() {
        return accountId;
    }

    public SocialNetworkInfo setAccountId(int accountId) {
        this.accountId = accountId;
        return this;
    }

    public SocialNetworks getServiceName() {
        return serviceName;
    }

    public SocialNetworkInfo setServiceName(SocialNetworks serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public SocialNetworkInfo setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SocialNetworkInfo setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getLastToken() {
        return lastToken;
    }

    public SocialNetworkInfo setLastToken(String lastToken) {
        this.lastToken = lastToken;
        return this;
    }

    public String getAdditionalTokenField() {
        return additionalTokenField;
    }

    public SocialNetworkInfo setAdditionalTokenField(String additionalTokenField) {
        this.additionalTokenField = additionalTokenField;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (isNull(obj) || getClass() != obj.getClass()) return false;

        SocialNetworkInfo socialNetworkInfo = (SocialNetworkInfo) obj;

        return new EqualsBuilder()
                .append(accountId, socialNetworkInfo.accountId)
                .append(serviceName, socialNetworkInfo.serviceName)
                .append(login, socialNetworkInfo.login)
                .append(password, socialNetworkInfo.password)
                .append(lastToken, socialNetworkInfo.lastToken)
                .append(additionalTokenField, socialNetworkInfo.additionalTokenField)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(accountId)
                .append(serviceName)
                .append(login)
                .append(password)
                .append(lastToken)
                .append(additionalTokenField)
                .toHashCode();
    }
}

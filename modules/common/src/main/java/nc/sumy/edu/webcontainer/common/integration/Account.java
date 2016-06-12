package nc.sumy.edu.webcontainer.common.integration;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import static java.util.Objects.isNull;

public class Account {
    private long    accountId;
    private SocialNetworks serviceName;
    private String  login;
    private String  password;
    private String  lastToken;
    private String  rawResponse;

    public Account() {}

    public Account(long accountId, SocialNetworks serviceName, String login,
                   String password, String lastToken, String rawResponse) {
        this.accountId = accountId;
        this.serviceName = serviceName;
        this.login = login;
        this.password = password;
        this.lastToken = lastToken;
        this.rawResponse = rawResponse;
    }

    public long getAccountId() {
        return accountId;
    }

    public Account setAccountId(long accountId) {
        this.accountId = accountId;
        return this;
    }

    public SocialNetworks getServiceName() {
        return serviceName;
    }

    public Account setServiceName(SocialNetworks serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public Account setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Account setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getLastToken() {
        return lastToken;
    }

    public Account setLastToken(String lastToken) {
        this.lastToken = lastToken;
        return this;
    }

    public String getRawResponse() {
        return rawResponse;
    }

    public Account setRawResponse(String rawResponse) {
        this.rawResponse = rawResponse;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (isNull(obj) || getClass() != obj.getClass()) return false;

        Account account = (Account) obj;

        return new EqualsBuilder()
                .append(accountId, account.accountId)
                .append(serviceName, account.serviceName)
                .append(login, account.login)
                .append(password, account.password)
                .append(lastToken, account.lastToken)
                .append(rawResponse, account.rawResponse)
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
                .append(rawResponse)
                .toHashCode();
    }
}

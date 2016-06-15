package nc.sumy.edu.webapp.orm.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import static java.util.Objects.isNull;

public class Portal {
    private int userId;
    private int accountId;

    public Portal() {}

    public Portal(int userId, int accountId) {
        this.userId = userId;
        this.accountId = accountId;
    }

    public int getUserId() {
        return userId;
    }

    public Portal setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public long getAccountId() {
        return accountId;
    }

    public Portal setAccountId(int accountId) {
        this.accountId = accountId;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (isNull(obj) || getClass() != obj.getClass()) return false;

        Portal portal = (Portal) obj;

        return new EqualsBuilder()
                .append(userId, portal.userId)
                .append(accountId, portal.accountId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .append(accountId)
                .toHashCode();
    }
}

package nc.sumy.edu.webapp.database.stubs;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Portal {
    private long userId;
    private long accountId;

    public long getUserId() {
        return userId;
    }

    public Portal setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public long getAccountId() {
        return accountId;
    }

    public Portal setAccountId(long accountId) {
        this.accountId = accountId;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

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

package nc.sumy.edu.webapp.database.stubs;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import static java.util.Objects.isNull;

public class Service {
    private String serviceName;

    public Service() {}

    public Service(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public Service setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (isNull(obj) || getClass() != obj.getClass()) return false;

        Service service = (Service) obj;

        return new EqualsBuilder()
                .append(serviceName, service.serviceName)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(serviceName)
                .toHashCode();
    }
}

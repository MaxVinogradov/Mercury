package nc.sumy.edu.webapp.integration.common;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ResultOfPostSubmit {
    private SocialNetworkInfo info;
    private boolean postSucceed;

    public ResultOfPostSubmit(SocialNetworkInfo info, boolean postSucceed) {
        this.info = info;
        this.postSucceed = postSucceed;
    }

    public SocialNetworkInfo getInfo() {
        return info;
    }

    public boolean isPostSucceed() {
        return postSucceed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResultOfPostSubmit that = (ResultOfPostSubmit) o;
        if (postSucceed != that.postSucceed) return false;
        return info != null ? info.equals(that.info) : that.info == null;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(postSucceed)
                .append(info)
                .toHashCode();
    }

}

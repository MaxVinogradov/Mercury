package nc.sumy.edu.webcontainer.common.integration;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ResultOfPostSubmit {
    private final SocialNetworkInfo info;
    private final boolean postSucceed;

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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ResultOfPostSubmit that = (ResultOfPostSubmit) obj;
        if (postSucceed != that.postSucceed) return false;
        return info == null ? that.info == null : info.equals(that.info);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(15, 39)
                .append(postSucceed)
                .append(info)
                .toHashCode();
    }

}

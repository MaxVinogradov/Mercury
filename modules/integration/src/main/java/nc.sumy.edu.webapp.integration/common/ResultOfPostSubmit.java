package nc.sumy.edu.webapp.integration.common;

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
}

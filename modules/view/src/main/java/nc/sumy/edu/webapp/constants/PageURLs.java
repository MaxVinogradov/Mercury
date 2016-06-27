package nc.sumy.edu.webapp.constants;

public enum PageURLs {
    LOG_IN_PAGE("/login.jsp"),
    SIGN_UP_PAGE("/signup.jsp"),
    APP_POSTS_PAGE("/app_posts.jsp"),
    CONNECT_PAGE("/connect.jsp"),
    CONNECT_VK_PAGE("/connect_vk.jsp"),
    CREATE_POST_PAGE("/create_post.jsp"),
    INDEX_PAGE("/index.html");


    private final String url;

    PageURLs(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.url;
    }
}

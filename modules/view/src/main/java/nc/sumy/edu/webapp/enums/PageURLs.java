package nc.sumy.edu.webapp.enums;

public enum PageURLs {
    LOG_IN_PAGE("view/login.jsp"),
    SIGN_UP_PAGE("view/signup.jsp"),
    APP_POSTS_PAGE("view/app_posts.jsp"),
    CONNECT_PAGE("view/connect.jsp"),
    CONNECT_VK_PAGE("view/connect_vk.jsp"),
    CREATE_POST_PAGE("view/create_post.jsp"),
    INDEX_PAGE("view/index.html");


    private final String url;

    PageURLs(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.url;
    }
}

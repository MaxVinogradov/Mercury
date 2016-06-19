package nc.sumy.edu.webapp.enums;

public enum Attributes {
    LOGIN("login"),
    PASSWORD("password"),
    USER_ID("user_id"),
    APPLICATION_POSTS("application_posts"),
    MESSAGE("message"),
    POSTING_RESULTS("posting_results");


    private final String attribute;

    Attributes(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return this.attribute;
    }
}

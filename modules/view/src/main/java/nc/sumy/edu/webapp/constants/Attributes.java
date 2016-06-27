package nc.sumy.edu.webapp.constants;

public enum Attributes {

    USER_ID("user_id"),

    LOGIN("login"),
    PASSWORD("password"),
    MAIL("mail"),
    BIRTH_DATE("birthDate"),

    APPLICATION_POSTS("application_posts"),
    MESSAGE("message"),
    POSTING_RESULTS("posting_results"),
    LOGIN_ERROR("log_in_error"),
    SIGN_UP_ERROR("sign_up_error");


    private final String attribute;

    Attributes(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return this.attribute;
    }
}

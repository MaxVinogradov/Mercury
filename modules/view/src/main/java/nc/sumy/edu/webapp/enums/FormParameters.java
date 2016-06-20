package nc.sumy.edu.webapp.enums;

public enum FormParameters {
    LOG_IN_PARAM("login");

    private final String parameter;

    FormParameters(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public String toString() {
        return this.parameter;
    }
}

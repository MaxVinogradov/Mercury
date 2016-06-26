package nc.sumy.edu.webapp.enums;

public enum PreparedHtmlTags {

    TD_OP("<td>"),

    TD_CL("</td>"),

    TR_OP("<tr>"),

    TR_CL("</tr>"),

    SPAN_OP_GI("<span "),

    SPAN_CL("</span>"),

    DIV_OP("<div class=\"container\">"),

    DIV_CL("</div>"),

    P_OP("<p>"),

    P_DANGER_OP("<p class=\"text-danger\" style=\"font-weight: bold;\">"),

    P_CL("</p>"),

    P_POST_OP("<p class=\"post-text\">"),

    P_HEADER_OP("<p class=\"text-center text-primary post-header\">"),

    P_DATE_OP("<p class=\"text-right \" style=\"font-size:1.1em;\"><em>"),

    P_DATE_CL("</em></p>"),

    POST_CONTAINER_OP(" <div class=\"col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1 " +
                        "col-sm-10 col-sm-offset-1 col-xs-10 col-xs-offset-1 post-container shadow-affect\">");


    private final String preparedTag;


    PreparedHtmlTags(final String preparedTag)  {
        this.preparedTag = preparedTag;
    }

    @Override
    public String toString() {
        return this.preparedTag;
    }
}

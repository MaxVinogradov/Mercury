package nc.sumy.edu.webapp.viewProcessors;

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

    P_CL("</p>"),

    H3_OP("<h3 class=\"text-center\">"),

    H3_CL("</h3>");


    private final String preparedTag;


    PreparedHtmlTags(final String preparedTag)  {
        this.preparedTag = preparedTag;
    }

    @Override
    public String toString() {
        return this.preparedTag;
    }
}

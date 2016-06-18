package servlets.publishing;

public class HtmlProcessor {
    private static final String TD_OP = "<td>";
    private static final String TD_CL = "</td>";
    private static final String TR_OP = "<tr>";
    private static final String TR_CL = "</tr>";

    public String createTableRow(String... data) {
        StringBuilder builder = new StringBuilder();
        builder.append(TR_OP);
        for (int i = 0; i < data.length; i++) {
            builder.append(TD_OP)
                    .append(data)
                    .append(TD_CL);
        }
        builder.append(TR_CL);
        return builder.toString();
    }

}

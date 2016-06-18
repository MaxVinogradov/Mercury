package servlets.publishing;

import nc.sumy.edu.webapp.orm.domain.Post;

import java.util.Collection;

public class HtmlProcessor {
    private static final String TD_OP = "<td>";
    private static final String TD_CL = "</td>";
    private static final String TR_OP = "<tr>";
    private static final String TR_CL = "</tr>";
    private static final String SPAN_OP_GI = "<span ";
    private static final String SPAN_CL = "</span>";
    private static final String DIV_OP = "<div class=\"container\">";
    private static final String DIV_CL = "</div>";
    private static final String P_OP = "<p>";
    private static final String P_CL = "</p>";
    private static final String H3_OP = "<h3 class=\"text-center\">";
    private static final String H3_CL = "</h3>";

    public String createTableRow(String... data) {
        StringBuilder builder = new StringBuilder();
        builder.append(TR_OP);
        for (String aData : data) {
            builder.append(TD_OP)
                    .append(aData)
                    .append(TD_CL);
        }
        builder.append(TR_CL);
        return builder.toString();
    }

    public String createGlyphicon(String glyphiconType) {
        return SPAN_OP_GI + "class=\"glyphicon glyphicon-" + glyphiconType + "\">" + SPAN_CL;
    }

    public String createPostList(Collection<Post> posts) {
        StringBuilder builder = new StringBuilder();
        for (Post post: posts) {
            builder.append(DIV_OP)
                    .append(createPost(post))
                    .append(DIV_CL);
        }
        return builder.toString();
    }

    private String createPost(Post post) {
        return H3_OP + "Mercury" + H3_CL +
                P_OP + post.getBody() + P_CL +
                P_OP + post.getPublishDate() + P_CL;
    }

}

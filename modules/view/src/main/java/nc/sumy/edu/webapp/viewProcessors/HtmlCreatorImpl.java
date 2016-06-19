package nc.sumy.edu.webapp.viewProcessors;

import nc.sumy.edu.webapp.orm.domain.Post;

import java.util.Collection;

import static nc.sumy.edu.webapp.viewProcessors.PreparedHtmlTags.*;

public class HtmlCreatorImpl {

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

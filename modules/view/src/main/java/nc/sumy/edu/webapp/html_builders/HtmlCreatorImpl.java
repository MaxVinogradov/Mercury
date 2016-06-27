package nc.sumy.edu.webapp.html_builders;

import nc.sumy.edu.webapp.orm.domain.Post;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;

import static java.util.Locale.*;
import static nc.sumy.edu.webapp.constants.PreparedHtmlTags.*;

public class HtmlCreatorImpl implements HtmlCreator {

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
        ListIterator iterator = ((LinkedList)posts).listIterator(posts.size());
        StringBuilder builder = new StringBuilder();
        while(iterator.hasPrevious()) {
            builder.append(POST_CONTAINER_OP)
                    .append(createPost((Post) iterator.previous()))
                    .append(DIV_CL);

        }
        return builder.toString();
    }

    public String createErrorMassage(String massage) {
        return P_DANGER_OP + massage + P_CL;
    }

    private String createPost(Post post) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", ENGLISH);
        return P_HEADER_OP + "Mercury" + P_CL +
                P_POST_OP + post.getBody() + P_CL +
                P_DATE_OP  + formatter.format(post.getPublishDate().getTime()) + P_DATE_CL;
    }

}

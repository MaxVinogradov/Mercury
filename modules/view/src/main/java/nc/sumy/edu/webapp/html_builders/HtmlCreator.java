package nc.sumy.edu.webapp.html_builders;

import nc.sumy.edu.webapp.orm.domain.Post;

import java.util.Collection;

public interface HtmlCreator {

    String createTableRow(String... data);

    String createGlyphicon(String glyphiconType);

    String createPostList(Collection<Post> posts);

    String createErrorMassage(String massage);

}

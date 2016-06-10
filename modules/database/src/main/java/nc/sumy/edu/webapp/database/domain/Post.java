package nc.sumy.edu.webapp.database.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;
import java.util.Date;

import static java.util.Objects.isNull;

public class Post {
    private long        postId;
    private long        userId;
    private Calendar    publishDate;
    private String      title;
    private String      body;

    public Post() {}

    public Post(long postId, long userId, Calendar publishDate, String title, String body) {
        this.postId = postId;
        this.userId = userId;
        this.publishDate = publishDate;
        this.title = title;
        this.body = body;
    }

    public long getPostId() {
        return postId;
    }

    public Post setPostId(long postId) {
        this.postId = postId;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public Post setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public Calendar getPublishDate() {
        return publishDate;
    }

    public Post setPublishDate(Date publishDate) {
        this.publishDate = Calendar.getInstance();
        this.publishDate.setTime(publishDate);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Post setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getBody() {
        return body;
    }

    public Post setBody(String body) {
        this.body = body;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (isNull(obj) || getClass() != obj.getClass()) return false;

        Post post = (Post) obj;

        return new EqualsBuilder()
                .append(postId, post.postId)
                .append(userId, post.userId)
                .append(publishDate, post.publishDate)
                .append(title, post.title)
                .append(body, post.body)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(postId)
                .append(userId)
                .append(publishDate)
                .append(title)
                .append(body)
                .toHashCode();
    }
}

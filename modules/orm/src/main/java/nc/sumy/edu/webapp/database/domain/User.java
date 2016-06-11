package nc.sumy.edu.webapp.database.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;
import java.util.Date;

import static java.util.Objects.isNull;

public class User {
    private long userId;
    private String  login;
    private String  password;
    private String  mail;
    private Calendar publishDate;

    public User() {}

    public User(long userId, String login, String password, String mail, Calendar publishDate) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.publishDate = publishDate;
    }

    public long getUserId() {
        return userId;
    }

    public User setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public User setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public Calendar getPublishDate() {
        return publishDate;
    }

    public User setPublishDate(Date publishDate) {
        this.publishDate = Calendar.getInstance();
        this.publishDate.setTime(publishDate);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (isNull(obj) || getClass() != obj.getClass()) return false;

        User user = (User) obj;

        return new EqualsBuilder()
                .append(userId, user.userId)
                .append(login, user.login)
                .append(password, user.password)
                .append(mail, user.mail)
                .append(publishDate, user.publishDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .append(login)
                .append(password)
                .append(mail)
                .append(publishDate)
                .toHashCode();
    }
}

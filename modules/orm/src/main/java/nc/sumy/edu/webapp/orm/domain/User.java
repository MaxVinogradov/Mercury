package nc.sumy.edu.webapp.orm.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Calendar;
import java.util.Date;

import static java.util.Objects.isNull;

public class User {
    private int userId;
    private String  login;
    private String  password;
    private String  mail;
    private Calendar birthDate;

    public User() {}

    public User(int userId, String login, String password, String mail, Calendar birthDate) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.birthDate = birthDate;
    }

    public int getUserId() {
        return userId;
    }

    public User setUserId(int userId) {
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

    public Calendar getBirthDate() {
        return birthDate;
    }

    public User setBirthDate(Date birthDate) {
        this.birthDate = Calendar.getInstance();
        this.birthDate.setTime(birthDate);
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
                .append(birthDate, user.birthDate)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .append(login)
                .append(password)
                .append(mail)
                .append(birthDate)
                .toHashCode();
    }
}

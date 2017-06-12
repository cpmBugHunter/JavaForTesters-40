package ru.pft40.mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class AccountData {

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Column(name = "username")
    private String username = "";

    @Column(name = "realname")
    private String realName = "";

    @Column(name = "email")
    private String email = "";

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public AccountData withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public AccountData withRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AccountData withEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public String toString() {
        return "AccountData{" + "id=" + id + ", username='" + username + '\'' + ", realName='" + realName + '\''
                + ", email='" + email + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountData that = (AccountData) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (realName != null ? !realName.equals(that.realName) : that.realName != null) return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (realName != null ? realName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}

package by.aplevich.horcerace.datamodel;

import by.aplevich.horcerace.datamodel.enums.UserRole;

import javax.persistence.*;
import java.util.List;

/**
 * Contains user data
 */
@Entity
public class UserAccount extends AbstractEntity {
    @Column
    private String name;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.ORDINAL)
    private UserRole role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
package com.salah.projectmanager.domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by bnadem on 5/30/17.
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idRole;

    private String role;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}

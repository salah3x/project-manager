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
}

package com.salah.projectmanager.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by bnadem on 5/30/17.
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Length(min = 3, max = 20, message = "Username between 3 and 20 char")
    private String username;

    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email must not be empty")
    private String email;

    @Length(min = 3, max = 20, message = "Password between 3 and 20 char")
    private String password;

    private String avatar;

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<Role> roles;

    @ManyToMany
    @JoinTable(
            name = "user_project",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id")
    )
    private Collection<Project> projects;

    @ManyToOne
    @JoinTable(
            name = "user_task",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id")
    )
    private Collection<Task> tasks;
}

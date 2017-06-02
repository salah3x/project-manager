package com.salah.projectmanager.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by bnadem on 6/2/17.
 */
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Length(min = 3, max = 40, message = "Name between 3 and 40 characters")
    private String name;

    @Length(max = 800, message = "Description must be less than 800 characters")
    private String description;

    private String cover;

    private String state;

    private Date initDate;

    @ManyToMany(mappedBy = "projects")
    private Collection<User> users;

    @OneToMany
    @JoinTable(
            name = "project_task",
            joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id")
    )
    private Collection<Task> tasks;
}

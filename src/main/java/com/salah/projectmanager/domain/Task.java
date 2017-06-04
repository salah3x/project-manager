package com.salah.projectmanager.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by bnadem on 6/2/17.
 */
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Length(min = 3, max = 50, message = "Title must be between 3 and 50 characters")
    private String title;

    @Length(min = 3, max = 1000, message = "Description must be between 3 and 1000 characters")
    private String description;

    private String state;

    private Date initDate;

    private Date finishDate;

    private Date realFinishDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getInitDate() {
        return initDate;
    }

    public void setInitDate(Date initDate) {
        this.initDate = initDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Date getRealFinishDate() {
        return realFinishDate;
    }

    public void setRealFinishDate(Date realFinishDate) {
        this.realFinishDate = realFinishDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state='" + state + '\'' +
                ", initDate=" + initDate +
                ", finishDate=" + finishDate +
                ", realFinishDate=" + realFinishDate +
                ", project=" + project +
                ", user=" + user +
                '}';
    }
}

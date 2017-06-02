package com.salah.projectmanager.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by bnadem on 6/2/17.
 */
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
    private Project project;

    @ManyToOne
    private User user;
}

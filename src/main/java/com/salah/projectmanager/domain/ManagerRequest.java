package com.salah.projectmanager.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

/**
 * Created by bnadem on 6/2/17.
 */
public class ManagerRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Length(min = 3, max = 100, message = "Message must be between 3 and 100 characters")
    private String message;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}

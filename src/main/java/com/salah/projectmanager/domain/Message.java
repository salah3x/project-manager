package com.salah.projectmanager.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by bnadem on 6/2/17.
 */
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    private String from;

    @Length(min = 3, max = 200, message = "Message must be between 3 and 200 characters")
    private String message;

    private Date date;
}

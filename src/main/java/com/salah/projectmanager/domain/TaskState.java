package com.salah.projectmanager.domain;

/**
 * Created by bnadem on 6/5/17.
 */
public enum TaskState {
    ASSIGNED ("Assigned"),
    CONFIRMED ("Confirmed"),
    APPROVED ("Approved");

    private final String name;

    TaskState(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}

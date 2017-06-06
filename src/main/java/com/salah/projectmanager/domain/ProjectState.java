package com.salah.projectmanager.domain;

/**
 * Created by bnadem on 6/5/17.
 */
public enum ProjectState {

    STARTED ("Started"),
    STOPPED ("Stopped"),
    FINISHED ("Finished");

    private final String name;

    ProjectState(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }
}

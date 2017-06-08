package com.salah.projectmanager.service;

import com.salah.projectmanager.domain.Project;
import com.salah.projectmanager.domain.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bnadem on 6/2/17.
 */

public interface ManagerService {
    void addProject(Project project, String user);

    void updateProject(Project project, String user);

    void deleteProject(int idProject, String user);

    void addTask(Task task, int idProject, String user);

    void updateTask(Task task, int idProject, String user);

    void deleteTask(int idTask, int idProject, String user);

    void approveTask(int idTask, String user);

    String getStatistic(int idProject, String user);
}

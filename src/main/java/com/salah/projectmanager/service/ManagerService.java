package com.salah.projectmanager.service;

import com.salah.projectmanager.domain.Project;
import com.salah.projectmanager.domain.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by bnadem on 6/2/17.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public interface ManagerService {
    void addProject(Project project, int idUser);

    void updateProject(Project project, int idUser);

    void deleteProject(int idProject, int idUser);

    void addTask(Task task, int idProject, int idUser);

    void updateTask(Task task, int idUser);

    void deleteTask(int idTask, int idUser);

    void approveTask(int idTask, int idUser);

    String getStatistic(int idProject, int idUser);
}

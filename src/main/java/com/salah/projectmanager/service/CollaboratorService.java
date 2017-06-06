package com.salah.projectmanager.service;

import com.salah.projectmanager.domain.ManagerRequest;
import com.salah.projectmanager.domain.Project;
import com.salah.projectmanager.domain.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bnadem on 6/2/17.
 */
public interface CollaboratorService {
    void sendManagerRequest(ManagerRequest req, String username);

    void confirmTask(int idTask, String username);

    List<Project> getAllProjects(String username);

    Project getProjectWithTasks(int idProject, String username);

    List<Task> getAllTasks(String username);
}
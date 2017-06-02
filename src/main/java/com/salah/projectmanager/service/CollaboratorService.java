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
@Service
@Transactional(rollbackFor = Exception.class)
public interface CollaboratorService {
    void sendManagerRequest(ManagerRequest req, int idUser);
    void confirmTask(int idTask, int idUser);
    List<Project> getAllProjects(int idUser);
    Project getProjectWithTasks(int idProject, int idUser);
    List<Task> getAllTasks(int idUser);
}

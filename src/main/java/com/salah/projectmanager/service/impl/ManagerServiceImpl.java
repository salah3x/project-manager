package com.salah.projectmanager.service.impl;

import com.salah.projectmanager.domain.Project;
import com.salah.projectmanager.domain.Task;
import com.salah.projectmanager.domain.User;
import com.salah.projectmanager.repo.ProjectRepository;
import com.salah.projectmanager.repo.UserRepository;
import com.salah.projectmanager.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bnadem on 6/7/17.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addProject(Project project, String user) {
        project.setInitDate(new Date());
        project.setUsers(new ArrayList<User>() {
            {
                add(userRepository.findByUsername(user));
            }
        });
        projectRepository.save(project);
    }

    @Override
    public void updateProject(Project project, String user) {
        Project p = projectRepository.findOne(project.getId());
        if (project != null && p.getUsers().contains(userRepository.findByUsername(user))) {
            p.setName(project.getName());
            p.setDescription(project.getDescription());
            p.setCover(project.getCover());
            p.setState(project.getState());
            projectRepository.save(p);
        }
    }

    @Override
    public void deleteProject(int idProject, String user) {
        Project project = projectRepository.findOne(idProject);
        if (project != null && project.getUsers().contains(userRepository.findByUsername(user))) {
            projectRepository.delete(idProject);
        }
    }

    @Override
    public void addTask(Task task, int idProject, String user) {

    }

    @Override
    public void updateTask(Task task, int idProject, String user) {

    }

    @Override
    public void deleteTask(int idTask, int idProject, String user) {

    }

    @Override
    public void approveTask(int idTask, String user) {

    }

    @Override
    public String getStatistic(int idProject, String user) {
        return null;
    }
}

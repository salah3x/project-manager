package com.salah.projectmanager.service.impl;

import com.salah.projectmanager.domain.*;
import com.salah.projectmanager.repo.ManagerRequestRepository;
import com.salah.projectmanager.repo.ProjectRepository;
import com.salah.projectmanager.repo.TaskRepository;
import com.salah.projectmanager.repo.UserRepository;
import com.salah.projectmanager.service.CollaboratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bnadem on 6/6/17.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CollaboratorServiceImpl implements CollaboratorService {

    @Autowired
    private ManagerRequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void sendManagerRequest(ManagerRequest req, String username) {
        User user =userRepository.findByUsername(username);
        if (user.getRequest() == null) {
            req.setUser(user);
            requestRepository.save(req);
        }
    }

    @Override
    public void confirmTask(int idTask, String username) {
        Task task = taskRepository.findOne(idTask);
        if (task != null && task.getUser().equals(userRepository.findByUsername(username))){
            task.setState(TaskState.CONFIRMED);
            taskRepository.save(task);
        }
    }

    @Override
    public List<Project> getAllProjects(String username) {
        return userRepository.findByUsername(username).getProjects();
    }

    @Override
    public Project getProjectWithTasks(int idProject, String username) {
       return projectRepository.findOne(idProject);

    }

    @Override
    public List<Task> getAllTasks(String username) {
        return userRepository.findByUsername(username).getTasks();
    }
}

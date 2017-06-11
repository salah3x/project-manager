package com.salah.projectmanager.service.impl;

import com.salah.projectmanager.domain.Project;
import com.salah.projectmanager.domain.Task;
import com.salah.projectmanager.domain.User;
import com.salah.projectmanager.repo.ProjectRepository;
import com.salah.projectmanager.repo.TaskRepository;
import com.salah.projectmanager.repo.UserRepository;
import com.salah.projectmanager.service.ManagerService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Autowired
    private TaskRepository taskRepository;

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
    public Task getTask(int idT, int idP, String user) {
        Project project = projectRepository.findOne(idP);
        if (project != null && project.getUsers().contains(userRepository.findByUsername(user))) {
            return taskRepository.findOne(idT);
        }
        return null;
    }

    @Override
    public void addTask(Task task, int idProject, String user) {
        Project project = projectRepository.findOne(idProject);
        if (project!=null && project.getUsers().contains(userRepository.findByUsername(user))) {
            task.setInitDate(new Date());
            task.setProject(project);
            taskRepository.save(task);
        }
    }

    @Override
    public void updateTask(Task task, int idProject, String user) {

    }

    @Override
    public void deleteTask(int idTask, int idProject, String user) {
        Project project = projectRepository.findOne(idProject);
        if (project.getUsers().contains(userRepository.findByUsername(user))) {
            taskRepository.delete(idTask);
        }
    }

    @Override
    public void approveTask(int idTask, String user) {

    }

    @Override
    public Map<String, Object> getStatistic(int idProject, String user) {
        Project p =projectRepository.findOne(idProject);
        Map<String, Object> map = new HashMap<String, Object>();
        if (p.getUsers().contains(userRepository.findByUsername(user))) {
            map.put("name", p.getName());
            map.put("startedAt", p.getInitDate().toString());
            List<String> finishDates = new ArrayList<String>();
            List<String> realFinishDates = new ArrayList<String>();
            Date shouldFinish = p.getInitDate();
            Date willFinish = p.getInitDate();
            Boolean completed = true;
            for (Task t : p.getTasks()) {
                finishDates.add(t.getFinishDate().toString());
                shouldFinish = DateUtils.addMinutes(shouldFinish,t.getFinishDate().getMinutes());
                if (t.getRealFinishDate() != null) {
                    realFinishDates.add(t.getRealFinishDate().toString());
                    willFinish = DateUtils.addMinutes(willFinish, t.getRealFinishDate().getMinutes());
                }else {
                    completed = false;
                }
            }
            map.put("shouldFinish", shouldFinish.toString());
            map.put("finishDates", finishDates);
            if (completed){
                map.put("willFinish", willFinish.toString());

            }else {
                map.put("willFinish", "One or more tasks are not finished yet.");
            }
            map.put("realFinishDates", realFinishDates);
        }
        return map;
    }

    @Override
    public List<User> getCollaboratorsList() {
        return userRepository.getUsers("Collaborator");
    }
}

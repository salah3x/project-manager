package com.salah.projectmanager.service.impl;

import com.salah.projectmanager.domain.Message;
import com.salah.projectmanager.domain.Project;
import com.salah.projectmanager.domain.Role;
import com.salah.projectmanager.domain.User;
import com.salah.projectmanager.repo.MessageRepository;
import com.salah.projectmanager.repo.ProjectRepository;
import com.salah.projectmanager.repo.RoleRepository;
import com.salah.projectmanager.repo.UserRepository;
import com.salah.projectmanager.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bnadem on 6/4/17.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GuestServiceImpl implements GuestService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void signUp(User user) {
        userRepository.save(user);
        Role r=roleRepository.findOne(3);
        user.setRoles(new ArrayList<Role>(){
            {
                add(r);
            }
        });
    }

    @Override
    public User signIn(String usernameOrEmail) {
        return userRepository.findByUsername(usernameOrEmail);
    }

    @Override
    public void sendMessage(Message msg) {
        msg.setDate(new Date());
        messageRepository.save(msg);
    }

    @Override
    public List<Project> search(String keyword) {
        return projectRepository.search(keyword);
    }
}

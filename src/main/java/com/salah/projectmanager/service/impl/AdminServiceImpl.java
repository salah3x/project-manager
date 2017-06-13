package com.salah.projectmanager.service.impl;

import com.salah.projectmanager.domain.ManagerRequest;
import com.salah.projectmanager.domain.Message;
import com.salah.projectmanager.domain.Role;
import com.salah.projectmanager.domain.User;
import com.salah.projectmanager.repo.ManagerRequestRepository;
import com.salah.projectmanager.repo.MessageRepository;
import com.salah.projectmanager.repo.RoleRepository;
import com.salah.projectmanager.repo.UserRepository;
import com.salah.projectmanager.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bnadem on 6/8/17.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ManagerRequestRepository requestRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<ManagerRequest> getRequests() {
        return requestRepository.findAll();
    }

    @Override
    public void acceptRequest(int idReq) {
        User user = requestRepository.findOne(idReq).getUser();
        List<Role> list = user.getRoles();
        list.add(roleRepository.findByRole("Manager"));
        user.setRoles(list);
        userRepository.save(user);
        requestRepository.delete(idReq);
    }

    @Override
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }
}

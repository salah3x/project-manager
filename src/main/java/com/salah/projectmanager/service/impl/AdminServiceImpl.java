package com.salah.projectmanager.service.impl;

import com.salah.projectmanager.domain.ManagerRequest;
import com.salah.projectmanager.domain.Message;
import com.salah.projectmanager.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bnadem on 6/8/17.
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {
    @Override
    public List<ManagerRequest> getRequest() {
        return null;
    }

    @Override
    public void acceptRequest(int idReq) {

    }

    @Override
    public List<Message> getMessages() {
        return null;
    }
}

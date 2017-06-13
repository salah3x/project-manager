package com.salah.projectmanager.service;

import com.salah.projectmanager.domain.ManagerRequest;
import com.salah.projectmanager.domain.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bnadem on 6/2/17.
 */

public interface AdminService {
    List<ManagerRequest> getRequests();

    void acceptRequest(int idReq);

    List<Message> getMessages();
}

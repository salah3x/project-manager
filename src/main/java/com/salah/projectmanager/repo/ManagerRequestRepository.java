package com.salah.projectmanager.repo;

import com.salah.projectmanager.domain.ManagerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bnadem on 6/2/17.
 */
@Repository
public interface ManagerRequestRepository extends JpaRepository<ManagerRequest, Integer>{
}

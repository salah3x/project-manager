package com.salah.projectmanager.repo;

import com.salah.projectmanager.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bnadem on 6/2/17.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
}

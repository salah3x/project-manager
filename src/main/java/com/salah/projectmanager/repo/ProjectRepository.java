package com.salah.projectmanager.repo;

import com.salah.projectmanager.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bnadem on 6/2/17.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    @Query(value = "select p from Project p where p.name like %?1% or p.description like %?1%")
    List<Project> search(String keyword);
}

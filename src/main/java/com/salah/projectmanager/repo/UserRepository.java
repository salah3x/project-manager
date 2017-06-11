package com.salah.projectmanager.repo;

import com.salah.projectmanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by bnadem on 6/2/17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    @Query("select u from User u, Role r where r.role = ?1")
    List<User> getUsers(String role);
}

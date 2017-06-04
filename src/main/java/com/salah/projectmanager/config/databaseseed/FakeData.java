package com.salah.projectmanager.config.databaseseed;

import com.github.javafaker.Faker;
import com.salah.projectmanager.domain.Message;
import com.salah.projectmanager.domain.Role;
import com.salah.projectmanager.domain.User;
import com.salah.projectmanager.repo.MessageRepository;
import com.salah.projectmanager.repo.RoleRepository;
import com.salah.projectmanager.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by bnadem on 6/3/17.
 */
@Component
@Transactional
public class FakeData implements ApplicationListener<ContextRefreshedEvent> {

    private Faker faker;

    @Autowired
    private MessageRepository msgRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        faker = new Faker();

        Message m;
        for (int i = 0; i < 10; i++) {
            m = new Message();
            m.setSender(faker.internet().emailAddress());
            m.setMessage(faker.lorem().characters(20, 150));
            m.setDate(new Date());
            msgRepo.save(m);
        }

        Role r = new Role();
        r.setRole("Admin");
        roleRepo.save(r);
        Role r2 = new Role();
        r2.setRole("Manager");
        roleRepo.save(r2);
        Role r3 = new Role();
        r3.setRole("Collaborator");
        roleRepo.save(r3);

        User u = new User();
        u.setAvatar("img_avatar2.png");
        u.setCryptPassword(encoder.encode("123"));
        u.setEmail("salah.loukili@gmail.com");
        u.setUsername("salah");
        u.setRoles(new ArrayList<Role>() {
            {
                add(r);
                add(r2);
                add(r3);
            }
        });
        userRepo.save(u);
        User u2 = new User();
        u2.setAvatar("img_avatar1.png");
        u2.setCryptPassword(encoder.encode("colla"));
        u2.setEmail(faker.internet().emailAddress());
        u2.setUsername("colla");
        u2.setRoles(new ArrayList<Role>() {
            {
                add(r3);
            }
        });
        userRepo.save(u2);


    }
}

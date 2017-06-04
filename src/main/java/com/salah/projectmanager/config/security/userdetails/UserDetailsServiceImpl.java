package com.salah.projectmanager.config.security.userdetails;

import com.salah.projectmanager.domain.User;
import com.salah.projectmanager.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by bnadem on 6/4/17.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Converter<User, UserDetails> userUserDetailsConverter;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user.equals(null)) {
            throw new UsernameNotFoundException("No such user with username :" + username);
        }
        return userUserDetailsConverter.convert(user);
    }
}

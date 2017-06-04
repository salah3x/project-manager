package com.salah.projectmanager.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by bnadem on 5/29/17.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("daoAuthenticationProvider")
    private AuthenticationProvider authenticationProvider;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder,
                                                               UserDetailsService userDetailsService) {

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/", "/signup", "/search", "/wiki", "/message").permitAll()
                .antMatchers("/projects", "/projects/*", "/profile", "/task/*/confirm", "/managerReq/send").access("hasAuthority('Collaborator')")
                .antMatchers("/projects/new", "/projects/*/edit", "/projects/*/delete", "/projects/*/statistic", "/projects/*/newTask").access("hasAuthority('Manager')")
                .antMatchers("/task/*/edit", "/task/*/delete", "/task/*/approve").access("hasAuthority('Manager')")
                .antMatchers("/managerReq/*/accept", "/console/**").access("hasAuthority('Admin')")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/signin").defaultSuccessUrl("/projects", true).permitAll()
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/signin?signout").permitAll();
        //for H2 Database [Security hole | avoid in production]
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }
}
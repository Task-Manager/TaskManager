package com.projectmanager.taskmanager.services.impl;

import com.projectmanager.taskmanager.entities.User;
import com.projectmanager.taskmanager.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;


@Service("taskManagerDetailsServiceImpl")
public class TaskManagerDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public TaskManagerDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*
     * The idea behind this method is to get our user and make it object of type UserDetails.
     * This will give us the ability to login and do other things with our users.
     * We need to get a user by a given email. If the user does not exist, we will throw exception
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid User");
        } else {
            /*
                Here we get all of the user roles and create a collection of authorities.
                Then we create a new Spring Security User with the given email, password and authorities.
             */

            Set<GrantedAuthority> grantedAuthorities = user.getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toSet());

            return new org.springframework.security.core.userdetails.
                    User(user.getEmail(), user.getPassword(), grantedAuthorities);
        }
    }
}

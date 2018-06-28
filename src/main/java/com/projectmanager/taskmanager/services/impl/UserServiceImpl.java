package com.projectmanager.taskmanager.services.impl;

import com.projectmanager.taskmanager.entities.User;
import com.projectmanager.taskmanager.repositories.UserRepository;
import com.projectmanager.taskmanager.services.contracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User findByEmail(String email) {
        return this.repository.findByEmail(email);
    }

    @Override
    public void addNewUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User can't be null!");
        }

        this.repository.saveAndFlush(user);
    }

    /**
     * Checks if user exists by email.
     *
     * @return true - only if there is a user
     * into the db with this email, false other wise.
     */
    @Override
    public boolean checkIfUserExists(String email) {
        User user = this.repository.findByEmail(email);
        return user != null;
    }
}

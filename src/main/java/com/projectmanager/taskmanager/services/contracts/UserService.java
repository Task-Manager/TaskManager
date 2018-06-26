package com.projectmanager.taskmanager.services.contracts;

import com.projectmanager.taskmanager.entities.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    User findByEmail(String email);

    void addNewUser(User user);
}

package com.projectmanager.taskmanager.services.contracts;

import com.projectmanager.taskmanager.entities.Role;
import org.springframework.stereotype.Component;

@Component
public interface RoleService {
    Role findByName(String name);
}

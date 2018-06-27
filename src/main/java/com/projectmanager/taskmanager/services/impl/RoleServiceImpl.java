package com.projectmanager.taskmanager.services.impl;

import com.projectmanager.taskmanager.entities.Role;
import com.projectmanager.taskmanager.repositories.RoleRepository;
import com.projectmanager.taskmanager.services.contracts.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByName(String name) {
        return this.roleRepository.findByName(name);
    }
}

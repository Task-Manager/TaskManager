package com.projectmanager.taskmanager.controllers;

import com.projectmanager.taskmanager.entities.User;
import com.projectmanager.taskmanager.repositories.RoleRepository;
import com.projectmanager.taskmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public UserController(RoleRepository roleRepository,
                          UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("view", "user/register");
        return "base-layout";
    }

    @PostMapping("/register")
    public String processRegister(Model model, BindingResult result) {

        // FIXME
        model.addAttribute("user", new User());
        model.addAttribute("view", "user/register");
        return "base-layout";
    }

}

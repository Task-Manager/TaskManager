package com.projectmanager.taskmanager.controllers;

import com.projectmanager.taskmanager.services.contracts.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/project/{id}")
    public String delete(Model model, @PathVariable int id) {
        model.addAttribute("view", "home/tasks");
        return "base-layout";
    }
}

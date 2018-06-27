package com.projectmanager.taskmanager.controllers;

import com.projectmanager.taskmanager.entities.Project;
import com.projectmanager.taskmanager.services.contracts.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.projectmanager.taskmanager.constants.view.VariableNames.VIEW;

@Controller
public class IndexController {

    private ProjectService projectService;

    @Autowired
    public IndexController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Project> projects = this.projectService.findAll();
        model.addAttribute("projects", projects);
        model.addAttribute(VIEW, "home/index");
        return "base-layout";
    }
}

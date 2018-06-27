package com.projectmanager.taskmanager.controllers;

import com.projectmanager.taskmanager.entities.Task;
import com.projectmanager.taskmanager.services.contracts.ProjectService;
import com.projectmanager.taskmanager.services.contracts.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    private ProjectService projectService;
    private TaskService taskService;

    @Autowired
    public IndexController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Task> tasks = this.taskService.findAll();
        model.addAttribute("tasks", tasks);
        model.addAttribute("view", "home/index");
        return "base-layout";
    }
}

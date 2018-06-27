package com.projectmanager.taskmanager.controllers;

import com.projectmanager.taskmanager.entities.Task;
import com.projectmanager.taskmanager.services.contracts.ProjectService;
import com.projectmanager.taskmanager.services.contracts.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProjectController {

    private ProjectService projectService;
    private TaskService taskService;

    @Autowired
    public ProjectController(ProjectService projectService, TaskService taskService) {
        this.projectService = projectService;
        this.taskService = taskService;
    }

    @GetMapping("/project/{id}")
    public String delete(Model model, @PathVariable long id) {
        List<Task> tasks = this.taskService.findAllByProjectId(id);
        model.addAttribute("tasks", tasks);
        model.addAttribute("view", "home/tasks");
        return "base-layout";
    }
}

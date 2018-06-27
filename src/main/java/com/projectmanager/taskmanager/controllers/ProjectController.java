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
    public String projectId(Model model, @PathVariable long id) {
        List<Task> tasksByProjectId = this.taskService.findAllByProjectId(id);

        List<Task> tasksWithToDoStatus =
                this.taskService.findAllByStatus(tasksByProjectId, "ToDo");

        List<Task> tasksWithInProgressStatus =
                this.taskService.findAllByStatus(tasksByProjectId, "InProgress");

        List<Task> tasksWithFinishedStatus =
                this.taskService.findAllByStatus(tasksByProjectId, "Finished");


        model.addAttribute("todoTasks", tasksWithToDoStatus);
        model.addAttribute("inprogressTasks", tasksWithInProgressStatus);
        model.addAttribute("finishedTasks", tasksWithFinishedStatus);
        model.addAttribute("view", "home/tasks");
        return "base-layout";
    }
}

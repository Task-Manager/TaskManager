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

import static com.projectmanager.taskmanager.constants.task_statuses.TaskStatus.*;
import static com.projectmanager.taskmanager.constants.url.ReturnViewRoads.BASE_LAYOUT;
import static com.projectmanager.taskmanager.constants.view.HomePathValriables.HOME_TASKS;
import static com.projectmanager.taskmanager.constants.view.VariableNames.VIEW;

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
                this.taskService.findAllByStatus(tasksByProjectId, TASK_STATUS_TODO);

        List<Task> tasksWithInProgressStatus =
                this.taskService.findAllByStatus(tasksByProjectId, TASK_STATUS_IN_PROGRESS);

        List<Task> tasksWithFinishedStatus =
                this.taskService.findAllByStatus(tasksByProjectId, TASK_STATUS_FINISHED);


        model.addAttribute("todoTasks", tasksWithToDoStatus);
        model.addAttribute("inprogressTasks", tasksWithInProgressStatus);
        model.addAttribute("finishedTasks", tasksWithFinishedStatus);
        model.addAttribute(VIEW, HOME_TASKS);
        return BASE_LAYOUT;
    }
}

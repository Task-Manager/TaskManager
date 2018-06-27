package com.projectmanager.taskmanager.dtos;

import com.projectmanager.taskmanager.entities.Project;
import com.projectmanager.taskmanager.entities.User;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TaskDto implements Serializable {
    @NotNull
    private String label;

    @NotNull
    private User user;

    @NotNull
    private Project project;

    public TaskDto() {
        super();
    }

    public TaskDto(String label, User user, Project project) {
        this.label = label;
        this.user = user;
        this.project = project;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Project getProject() {
        return this.project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}

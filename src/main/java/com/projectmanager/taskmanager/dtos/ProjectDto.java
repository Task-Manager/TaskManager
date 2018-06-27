package com.projectmanager.taskmanager.dtos;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class ProjectDto implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String status;

    private List<UserDto> users;

    private List<TaskDto> tasks;

    public ProjectDto() {
        super();
    }

    public ProjectDto(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

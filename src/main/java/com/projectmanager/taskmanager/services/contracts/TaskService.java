package com.projectmanager.taskmanager.services.contracts;

import com.projectmanager.taskmanager.entities.Task;

import java.util.List;

public interface TaskService {
    List<Task> findAll();

    List<Task> findAllByProjectId(Long id);

    List<Task> findAllByStatus(List<Task> tasks, String status);
}

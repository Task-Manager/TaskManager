package com.projectmanager.taskmanager.services.impl;

import com.projectmanager.taskmanager.entities.Task;
import com.projectmanager.taskmanager.exceptions.ApplicationRuntimeException;
import com.projectmanager.taskmanager.repositories.TaskRepository;
import com.projectmanager.taskmanager.services.contracts.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return this.taskRepository.findAll();
    }

    @Override
    public List<Task> findAllByProjectId(Long id) {
        return this.taskRepository.findAllByProjectId(id);
    }

    /**
     * Finds all task's by project id and status id.
     *
     * @param id     the project id.
     * @param status the Task status that needs to be filtered.
     * @return List<Task> task's found with the given status.
     * @throws ApplicationRuntimeException if the given status is not valid,
     *                                     or if the project id does not exist.
     */
    @Override
    public List<Task> findAllByStatus(List<Task> tasksByProjectId, String status) {
        if (tasksByProjectId == null) {
            throw new ApplicationRuntimeException("Invalid project id. There is not such project in the DB.");
        }

        switch (status) {
            case "ToDo":
                return tasksByProjectId.stream()
                        .filter(task -> task.getStatus().equals(status))
                        .collect(Collectors.toList());

            case "InProgress":
                return tasksByProjectId.stream()
                        .filter(task -> task.getStatus().equals(status))
                        .collect(Collectors.toList());

            case "Finished":
                return tasksByProjectId.stream()
                        .filter(task -> task.getStatus().equals(status))
                        .collect(Collectors.toList());

            default:
                throw new ApplicationRuntimeException("Cannot find task's with the given status in the DB.");
        }
    }


}

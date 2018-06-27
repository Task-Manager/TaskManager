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
     * @param tasksByProjectId the tasks from some project.
     * @param status           the Task status that needs to be filtered.
     * @return List<Task> task's found with the given status.
     * @throws ApplicationRuntimeException if the given status is not valid,
     *                                     or if the project id does not exist.
     */
    @Override
    public List<Task> findAllByStatus(List<Task> tasksByProjectId, String status) {
        if (tasksByProjectId == null) {
            throw new ApplicationRuntimeException("Invalid project id. There is not such project in the DB.");
        }

        List<Task> tasks;
        switch (status) {
            case "ToDo":
                tasks = tasksByProjectId.stream()
                        .filter(task -> task.getStatus().equals(status))
                        .collect(Collectors.toList());
                break;
            case "InProgress":
                tasks = tasksByProjectId.stream()
                        .filter(task -> task.getStatus().equals(status))
                        .collect(Collectors.toList());
                break;
            case "Finished":
                tasks = tasksByProjectId.stream()
                        .filter(task -> task.getStatus().equals(status))
                        .collect(Collectors.toList());
                break;
            default:
                throw new ApplicationRuntimeException("Cannot find task's with the given status in the DB.");
        }

        for (Task task : tasks) {
            task.setSubContent();
        }

        return tasks;
    }


}

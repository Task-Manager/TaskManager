package com.projectmanager.taskmanager.services.impl;

import com.projectmanager.taskmanager.entities.Task;
import com.projectmanager.taskmanager.repositories.TaskRepository;
import com.projectmanager.taskmanager.services.contracts.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository ;

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
}

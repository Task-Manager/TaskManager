package com.projectmanager.taskmanager.services.impl;

import com.projectmanager.taskmanager.entities.Project;
import com.projectmanager.taskmanager.exceptions.ApplicationRuntimeException;
import com.projectmanager.taskmanager.repositories.ProjectRepository;
import com.projectmanager.taskmanager.services.contracts.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    private ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project findById(Long id) {
        if (id <= 0) {
            throw new ApplicationRuntimeException("Project Id cannot be below or equal to 0");
        }

        return this.projectRepository.findById(id);
    }

    @Override
    public Project getWithName(String name) {
        // TODO: Project name cannot be null it should be checked in the Front-end ?
        return this.projectRepository.findByName(name);
    }

    @Override
    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    @Override
    public List<Project> getAllWithName(String status) {

        // TODO; Define statuses ?
        //TODO: Make sure that the status is one of the possible.
        return this.projectRepository.findAllByStatus(status);
    }

    @Override
    public List<Project> getAllByStartDate(LocalDate date) {
        // TODO: Make sure that it works correctly, and the given date is in correct format!
        return this.projectRepository.findAllByCreatedDate(date);
    }
}

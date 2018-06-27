package com.projectmanager.taskmanager.services.contracts;

import com.projectmanager.taskmanager.entities.Project;

import java.util.Date;
import java.util.List;

public interface ProjectService {
    Project findById(Long id);

    Project getWithName(String name);

    List<Project> findAll();

    List<Project> getAllWithName(String status);

    List<Project> getAllByStartDate(Date date);
}

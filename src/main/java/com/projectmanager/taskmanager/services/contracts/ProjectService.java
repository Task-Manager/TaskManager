package com.projectmanager.taskmanager.services.contracts;

import com.projectmanager.taskmanager.entities.Project;

import java.time.LocalDate;
import java.util.List;

public interface ProjectService {
    Project findById(Long id);

    Project getWithName(String name);

    List<Project> getAllWithName(String status);

    List<Project> getAllByStartDate(LocalDate date);
}

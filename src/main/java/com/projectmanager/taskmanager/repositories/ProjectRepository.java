package com.projectmanager.taskmanager.repositories;

import com.projectmanager.taskmanager.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findById(Long id);

    Project findByName(String name);

    List<Project> findAllByStatus(String status);

    List<Project> findAllByCreatedDate(LocalDate date);
}

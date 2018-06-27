package com.projectmanager.taskmanager.entities;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label", nullable = false, length = 35)
    private String label;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @ManyToOne(targetEntity = Project.class)
    private Project project;

    @Column(name = "status", nullable = false)
    private String status;

    public Task() {
    }

    public Task(String label, User user, Project project) {
        this.label = label;
        this.user = user;
        this.project = project;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

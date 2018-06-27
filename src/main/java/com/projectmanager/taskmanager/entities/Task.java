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

    @Column(name = "content", length = 1000)
    private String content;


    /*
        Used to show the content body, for the task
        at the URL - project/id.

        @Transient - does not get saved in the DB.
     */
    @Transient
    private String subContent;

    public Task() {
        super();
    }

    public Task(String label, User user, Project project, String content) {
        this.label = label;
        this.user = user;
        this.project = project;
        this.content = content;
        this.setSubContent();
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

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubContent() {
        return this.subContent;
    }

    @Transient
    public void setSubContent() {
        int maxSubContentLen = 100;
        if (this.content.length() > maxSubContentLen) {
            this.subContent = content.substring(0, maxSubContentLen) + "...";
        } else {
            this.subContent = content;
        }
    }

}

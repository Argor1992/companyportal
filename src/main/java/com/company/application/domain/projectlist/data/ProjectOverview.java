package com.company.application.domain.projectlist.data;

import com.company.application.core.domain.IProject;
import com.company.application.data.project.entity.ProjectState;

import java.time.LocalDate;

public class ProjectOverview implements IProject {
    private final int id;
    private String name;
    private String description;
    private double amount;
    private LocalDate date;
    private ProjectState projectState;
    private int priority;

    public ProjectOverview(int id, String name, String description, double amount, LocalDate date, ProjectState projectState, int priority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.projectState = projectState;
        this.priority = priority;
    }

    @Override
    public Integer getId() { return id; }
    @Override
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    @Override
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    @Override
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    @Override
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    @Override
    public ProjectState getProjectState() { return projectState; }
    public void setProjectState(ProjectState projectState) { this.projectState = projectState; }
    @Override
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
}

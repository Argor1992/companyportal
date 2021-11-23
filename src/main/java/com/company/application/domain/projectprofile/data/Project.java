package com.company.application.domain.projectprofile.data;

import com.company.application.domain.core.data.IClient;
import com.company.application.domain.core.data.IEmployee;
import com.company.application.domain.core.data.IProject;
import com.company.application.data.project.entity.ProjectState;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Thorsten Zieres, 1297197
 */
public class Project implements IProject {
    private final int id;
    private String name;
    private String description;
    private double amount;
    private LocalDate date;
    private ProjectState projectState;
    private int priority;
    private List<IEmployee> projectManagers;
    private List<IClient> projectClients;

    public Project(int id, String name, String description, double amount, LocalDate date, ProjectState projectState, int priority, List<IEmployee> projectManagers, List<IClient> projectClients) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.projectState = projectState;
        this.priority = priority;
        this.projectManagers = projectManagers;
        this.projectClients = projectClients;
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
    public List<IEmployee> getProjectManagers() { return projectManagers; }
    public void setProjectManagers(List<IEmployee> projectManagers) { this.projectManagers = projectManagers; }
    public List<IClient> getProjectClients() { return projectClients; }
    public void setProjectClients(List<IClient> projectClients) { this.projectClients = projectClients; }
}
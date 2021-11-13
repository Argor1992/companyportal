package com.company.application.data.project.entity;

import com.company.application.core.data.AbstractEntity;
import com.company.application.data.client.entity.ClientEntity;
import com.company.application.data.employee.entity.EmployeeEntity;
import com.company.application.data.employee.entity.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ProjectEntity extends AbstractEntity {
    private double amount;
    private LocalDate date;

    @Column(name="state", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectState projectState;

    private int priority;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="ProjectManager",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"project_id", "employee_id"}
            )
    )
    private Set<EmployeeEntity> projectManagers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="ProjectClient",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id"),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"project_id", "client_id"}
            )
    )
    private Set<ClientEntity> projectClients = new HashSet<>();

    public ProjectEntity() {
    }

    public ProjectEntity(double amount, LocalDate date, ProjectState projectState, int priority, Set<EmployeeEntity> projectManagers, Set<ClientEntity> projectClients) {
        this.amount = amount;
        this.date = date;
        this.projectState = projectState;
        this.priority = priority;
        this.projectManagers = projectManagers;
        this.projectClients = projectClients;
    }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public ProjectState getProjectState() { return projectState; }
    public void setProjectState(ProjectState projectState) { this.projectState = projectState; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
    public Set<EmployeeEntity> getProjectManagers() { return projectManagers; }
    public void setProjectManagers(Set<EmployeeEntity> projectManagers) { this.projectManagers = projectManagers; }
    public Set<ClientEntity> getProjectClients() { return projectClients; }
    public void setProjectClients(Set<ClientEntity> projectClients) { this.projectClients = projectClients; }
}
package com.company.application.data.project.entity;

import com.company.application.data.core.data.AbstractEntity;
import com.company.application.data.client.entity.ClientEntity;
import com.company.application.data.employee.entity.EmployeeEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Thorsten Zieres, 1297197
 */
@Entity
@Table(name = "Project")
public class ProjectEntity extends AbstractEntity implements Serializable {
    private String name;

    @Column(name = "description",unique=false,columnDefinition="VARCHAR(512)")
    private String description;
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

    public ProjectEntity(String name, String description, double amount, LocalDate date, ProjectState projectState, int priority) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.projectState = projectState;
        this.priority = priority;
    }

    public ProjectEntity(String name, String description, double amount, LocalDate date, ProjectState projectState, int priority, Set<EmployeeEntity> projectManagers, Set<ClientEntity> projectClients) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.projectState = projectState;
        this.priority = priority;
        this.projectManagers = projectManagers;
        this.projectClients = projectClients;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public ProjectState getProjectState() { return projectState; }
    public void setProjectState(ProjectState projectState) { this.projectState = projectState; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
    public List<EmployeeEntity> getProjectManagers() { return new ArrayList<>(projectManagers); }
    public void setProjectManagers(Set<EmployeeEntity> projectManagers) { this.projectManagers = projectManagers; }
    public List<ClientEntity> getProjectClients() { return new ArrayList<>(projectClients); }
    public void setProjectClients(Set<ClientEntity> projectClients) { this.projectClients = projectClients; }

    public void addProjectManager(EmployeeEntity employeeEntity) {
        projectManagers.add(employeeEntity);
    }

    public void addProjectClient(ClientEntity clientEntity) {
        projectClients.add(clientEntity);
    }
}
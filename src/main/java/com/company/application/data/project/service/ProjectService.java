package com.company.application.data.project.service;

import com.company.application.core.domain.IClient;
import com.company.application.core.domain.IEmployee;
import com.company.application.data.project.entity.ProjectEntity;
import com.company.application.data.project.repository.ProjectRepository;
import com.company.application.domain.projectlist.data.ProjectOverview;
import com.company.application.domain.projectprofile.data.Project;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Optional<ProjectOverview> getProjectOverview(int id) {
        return projectRepository.findProjectOverviewById(id).map(ProjectService.this::dtoToProjectOverview);
    }

    public List<ProjectOverview> getProjectOverviewList() {
        return projectRepository.findAllProjectOverviewsBy().stream().map(this::dtoToProjectOverview).collect(Collectors.toList());
    }

    @Transactional
    public boolean updateProject(ProjectOverview project) {
        return projectRepository.updateProjectOverview(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getAmount(),
                project.getDate(),
                project.getProjectState(),
                project.getPriority()
        ) == 1;
    }

    public Optional<Project> getFullProject(int id) {
        return projectRepository.findProjectByIdAndFetchEagerly(id).map(this::entityToProject);
    }

    private ProjectOverview dtoToProjectOverview(ProjectOverview project) {
        return new ProjectOverview(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getAmount(),
                project.getDate(),
                project.getProjectState(),
                project.getPriority()
        );
    }

    private Project entityToProject(ProjectEntity projectEntity) {
        return new Project(
                projectEntity.getId(),
                projectEntity.getName(),
                projectEntity.getDescription(),
                projectEntity.getAmount(),
                projectEntity.getDate(),
                projectEntity.getProjectState(),
                projectEntity.getPriority(),
                projectEntity.getProjectManagers().stream().map(employeeEntity -> new IEmployee() {
                    @Override
                    public int getId() { return employeeEntity.getId(); }
                    @Override
                    public String getPersonnelNumber() { return employeeEntity.getPersonnelNumber(); }
                    @Override
                    public String getFirstName() { return employeeEntity.getFirstName(); }
                    @Override
                    public String getLastName() { return employeeEntity.getLastName(); }
                    @Override
                    public String getEmail() { return employeeEntity.getEmail(); }
                    @Override
                    public String getPhone() { return employeeEntity.getPhone(); }
                    @Override
                    public LocalDate getDateOfBirth() { return employeeEntity.getDateOfBirth(); }
                    @Override
                    public String getProfilePicture() { return employeeEntity.getProfilePicture(); }
                }).collect(Collectors.toList()),
                projectEntity.getProjectClients().stream().map(clientEntity -> new IClient() {
                    @Override
                    public int getId() { return clientEntity.getId(); }
                    @Override
                    public String getName() { return clientEntity.getName(); }
                    @Override
                    public String getRepresentative() { return clientEntity.getRepresentative(); }
                    @Override
                    public String getEmail() { return clientEntity.getEmail(); }
                    @Override
                    public String getPhone() { return clientEntity.getPhone(); }
                }).collect(Collectors.toList())
        );
    }
}

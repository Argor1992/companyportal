package com.company.application.data.project.service;

import com.company.application.data.project.repository.ProjectRepository;
import com.company.application.domain.projectlist.data.ProjectOverview;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return projectRepository.findProjectOverviewById(id).map(project -> new ProjectOverview(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getAmount(),
                project.getDate(),
                project.getProjectState(),
                project.getPriority()
        ));
    }

    public List<ProjectOverview> getProjectOverviewList() {
        return projectRepository.findAllProjectOverviewsBy().stream().map(project -> new ProjectOverview(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getAmount(),
                project.getDate(),
                project.getProjectState(),
                project.getPriority()
        )).collect(Collectors.toList());
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
}

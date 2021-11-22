package com.company.application.data.project.controller;

import com.company.application.data.project.service.ProjectService;
import com.company.application.domain.projectlist.data.ProjectOverview;
import com.company.application.domain.projectprofile.data.Project;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    public Optional<ProjectOverview> getProjectOverview(int id) {
        if (id < 1)
            return Optional.empty();
        return projectService.getProjectOverview(id);
    }

    public List<ProjectOverview> getProjectOverviewList() {
        return projectService.getProjectOverviewList();
    }

    public boolean updateProject(ProjectOverview project) {
        if (project == null)
            return false;
        return projectService.updateProject(project);
    }

    public Optional<Project> getProject(int id) {
        if (id < 1)
            return Optional.empty();
        return projectService.getFullProject(id);
    }
}

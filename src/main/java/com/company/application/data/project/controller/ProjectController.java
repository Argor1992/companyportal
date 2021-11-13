package com.company.application.data.project.controller;

import com.company.application.data.project.service.ProjectService;
import org.springframework.stereotype.Controller;

@Controller
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
}

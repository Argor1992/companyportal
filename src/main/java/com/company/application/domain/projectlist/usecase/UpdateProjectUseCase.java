package com.company.application.domain.projectlist.usecase;

import com.company.application.data.project.controller.ProjectController;
import com.company.application.domain.projectlist.data.ProjectOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProjectUseCase {
    @Autowired
    private ProjectController projectController;

    public boolean updateProject(ProjectOverview project) {
        return projectController.updateProject(project);
    }
}

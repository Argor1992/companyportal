package com.company.application.domain.projectlist.usecase;

import com.company.application.data.project.controller.ProjectController;
import com.company.application.domain.core.usecase.IUpdateUseCase;
import com.company.application.domain.projectlist.data.ProjectOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateProjectUseCase implements IUpdateUseCase<ProjectOverview> {
    @Autowired
    private ProjectController projectController;

    @Override
    public boolean update(ProjectOverview selected) {
        return projectController.updateProject(selected);
    }
}

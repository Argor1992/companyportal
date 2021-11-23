package com.company.application.domain.projectprofile.usecase;

import com.company.application.data.project.controller.ProjectController;
import com.company.application.domain.projectprofile.data.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Thorsten Zieres, 1297197
 */
@Service
public class ProjectUseCase {
    @Autowired
    private ProjectController projectController;

    public Optional<Project> getProject(Integer id) {
        return projectController.getProject(id);
    }
}

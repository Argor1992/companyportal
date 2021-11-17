package com.company.application.domain.projectlist.usecase;

import com.company.application.core.security.SecurityController;
import com.company.application.data.employee.entity.Occupation;
import com.company.application.data.employee.entity.Role;
import com.company.application.data.project.controller.ProjectController;
import com.company.application.domain.projectlist.data.ProjectOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectListUseCase {
    @Autowired
    private ProjectController projectController;
    @Autowired
    private SecurityController securityController;

    public List<ProjectOverview> getProjectList() {
        return projectController.getProjectOverviewList();
    }

    public Optional<ProjectOverview> getProject(int integer) {
        return projectController.getProjectOverview(integer);
    }

    public boolean showUpdateMenu() {
        Optional<Role> role = securityController.getCurrentUserRole();
        Optional<Occupation> occupation = securityController.getCurrentUserOccupation();
        return role.isPresent() && occupation.isPresent() && (role.get() == Role.ADMIN || occupation.get() == Occupation.MANAGEMENT || occupation.get() == Occupation.DEVELOPER);
    }
}

package com.company.application.domain.projectdocuments.usecase;

import com.company.application.core.security.SecurityController;
import com.company.application.data.employee.entity.Occupation;
import com.company.application.data.project.controller.ProjectController;
import com.company.application.domain.core.data.IProject;
import com.company.application.domain.employeeprofile.data.Employee;
import com.company.application.domain.projectdocuments.data.ServerFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CreateFileTreeUseCase {

    @Autowired
    public ProjectController projectController;
    @Autowired
    private SecurityController securityController;

    public List<ServerFile> getProjectFiles() {
        Optional<Employee> currentUser = securityController.getCurrentUser();

        if (currentUser.isEmpty()) {
            return Collections.emptyList();
        }

        List<String> projectNames = currentUser.get().getProjects().stream().map(IProject::getName).collect(Collectors.toList());

        List<ServerFile> files = new ArrayList<>();
        projectNames.forEach(projectName -> {
            File projectDirectory = new File("./projectfiles/" + projectName);

            if (projectDirectory.exists() && projectDirectory.isDirectory()) {
                String[] fileContent = projectDirectory.list();

                files.add(getDirectoryFile(new ServerFile(projectDirectory.getName(), projectDirectory.getName(), true), fileContent, currentUser.get()));
            } else {
                if (projectDirectory.mkdir()) {
                    files.add(new ServerFile(projectName, projectName, true));
                }
            }
        });

        files.sort(Comparator.comparing(ServerFile::getFileName));

        return files;
    }

    private ServerFile getDirectoryFile(ServerFile parentFile, String[] directoryContent, Employee currentUser) {

        Arrays.stream(directoryContent).forEach(childName -> {
                    if (!childName.equals("Java") || currentUser.isInIt()) {
                        File f = new File("./projectfiles/" + parentFile.getPath(), childName);

                        if (f.isDirectory()) {
                            String[] fileContent = f.list();

                            parentFile.addChildren(getDirectoryFile(new ServerFile(childName, parentFile.getPath() + "/" + childName, true), fileContent, currentUser));
                        } else {
                            parentFile.addChildren(
                                    new ServerFile(childName, parentFile.getPath() + "/" + childName, false)
                            );
                        }

                    }
                }
        );

        return parentFile;
    }
}

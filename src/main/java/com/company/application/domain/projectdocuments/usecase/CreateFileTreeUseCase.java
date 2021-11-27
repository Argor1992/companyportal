package com.company.application.domain.projectdocuments.usecase;

import com.company.application.data.project.controller.ProjectController;
import com.company.application.domain.projectdocuments.data.ServerFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

@Service
public class CreateFileTreeUseCase {

    @Autowired
    public ProjectController projectController;

    public List<ServerFile> getProjectFiles() {
        List<String> projectNames = projectController.getAllProjectNames();

        List<ServerFile> files = new ArrayList<>();
        projectNames.forEach(projectName -> {
            File projectDirectory = new File("./projectfiles/" + projectName);

            if (projectDirectory.exists() && projectDirectory.isDirectory()) {
                String[] fileContent = projectDirectory.list();

                files.add(getDirectoryFile(new ServerFile(projectDirectory.getName(), projectDirectory.getName(), true), fileContent));
            } else {
                if (projectDirectory.mkdir()) {
                    files.add(new ServerFile(projectName, projectName, true));
                }
            }
        });

        files.sort(Comparator.comparing(ServerFile::getFileName));

        return files;
    }

    private ServerFile getDirectoryFile(ServerFile parentFile, String[] directoryContent) {

        Arrays.stream(directoryContent).forEach(childName -> {
                    File f = new File("./projectfiles/" + parentFile.getPath(), childName);

                    if (f.isDirectory()) {
                        String[] fileContent = f.list();

                        parentFile.addChildren(getDirectoryFile(new ServerFile(childName, parentFile.getPath() + "/" + childName, true), fileContent));
                    } else {
                        parentFile.addChildren(
                                new ServerFile(childName, parentFile.getPath() + "/" + childName, false)
                        );
                    }
                }
        );

        return parentFile;
    }
}

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

                files.add(getDirectoryFile(projectDirectory, fileContent));
            } else {
                if (projectDirectory.mkdir()) {
                    files.add(new ServerFile(projectName, true));
                }
            }
        });

        return files;
    }

    private ServerFile getDirectoryFile(File directory, String[] directoryContent) {
        ServerFile file = new ServerFile(directory.getName(), true);

        Arrays.stream(directoryContent).forEach(content -> {
                    File f = new File(directory, content);

                    if (f.isDirectory()) {
                        String[] fileContent = f.list();

                        file.addChildren(getDirectoryFile(f, fileContent));
                    } else {
                        file.addChildren(
                                new ServerFile(f.getName(), false)
                        );
                    }
                }
        );

        return file;
    }
}

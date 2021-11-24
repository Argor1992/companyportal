package com.company.application.views.projectdocuments;

import com.company.application.domain.projectdocuments.data.ServerFile;
import com.company.application.domain.projectdocuments.usecase.CreateFileTreeUseCase;
import com.company.application.views.projectdocuments.component.FileTree;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Section;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.Collections;
import java.util.List;

public class FileSelector extends Section {
    private final CreateFileTreeUseCase createFileTreeUseCase;

    public FileSelector(CreateFileTreeUseCase createFileTreeUseCase) {
        this.createFileTreeUseCase = createFileTreeUseCase;

        addClassNames("bg-contrast-20", "box-border", "flex", "flex-col", "flex-shrink-0", "overflow-auto", "h-full");
        setWidth("256px");

        List<ServerFile> files = createFileTreeUseCase.getProjectFiles();

        FileTree fileTree = new FileTree(ServerFile::getFileName);
        fileTree.setItems(files, file -> {
            if (file.isDirectory()) {
                return file.getChildren();
            } else {
                return Collections.emptyList();
            }
        });

        add(fileTree);
    }
}

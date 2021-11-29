package com.company.application.views.projectdocuments.component.filepicker;

import com.company.application.domain.projectdocuments.data.ServerFile;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;

import java.util.Collections;
import java.util.List;

public class FileTree extends TreeGrid<ServerFile> {
    public FileTree(List<ServerFile> projectFiles, List<ServerFile> expandedFiles) {
        setVerticalScrollingEnabled(true);

        addComponentHierarchyColumn(serverFile -> {
            HorizontalLayout layout = new HorizontalLayout();
            layout.setPadding(false);
            layout.setSpacing(false);
            layout.setAlignItems(FlexComponent.Alignment.CENTER);

            if (serverFile.isDirectory())
                layout.add(new Icon(VaadinIcon.FOLDER_O));
            else
                layout.add(new Icon(VaadinIcon.FILE_TEXT_O));

            Span span = new Span(serverFile.getFileName());
            span.addClassNames("pl-s");
            layout.add(span);

            return layout;
        });

        setItems(projectFiles, file -> {
            if (file.isDirectory()) {
                return file.getChildren();
            } else {
                return Collections.emptyList();
            }
        });

        expand(expandedFiles);
    }

    public void refreshFileTree(List<ServerFile> projectFiles, List<ServerFile> expandedFiles) {
        setItems(projectFiles, file -> {
            if (file.isDirectory()) {
                return file.getChildren();
            } else {
                return Collections.emptyList();
            }
        });

        expand(expandedFiles);
        getDataProvider().refreshAll();
    }
}

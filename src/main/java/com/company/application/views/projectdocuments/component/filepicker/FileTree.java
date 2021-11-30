package com.company.application.views.projectdocuments.component.filepicker;

import com.company.application.domain.projectdocuments.data.ServerFile;
import com.company.application.views.projectdocuments.ProjectDocumentsView;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileTree extends TreeGrid<ServerFile> {
    private final ProjectDocumentsView projectDocumentsView;
    private ServerFile selectedFile;
    private final List<ServerFile> projectFiles;
    private final List<ServerFile> expandedNodes;

    public FileTree(ProjectDocumentsView projectDocumentsView, List<ServerFile> projectFiles) {
        this.projectDocumentsView = projectDocumentsView;
        this.projectFiles = projectFiles;
        this.expandedNodes = new ArrayList<>();
        setVerticalScrollingEnabled(true);
        renderItems();
        handleFileSelection();
        rememberExpandAndCollapse();
        expand(expandedNodes);
    }

    private void handleFileSelection() {
        asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                selectedFile = event.getValue();
                projectDocumentsView.redrawTextArea();
            }
        });
    }

    private void renderItems() {
        setItems(projectFiles, file -> {
            if (file.isDirectory()) {
                return file.getChildren();
            } else {
                return Collections.emptyList();
            }
        });

        addComponentHierarchyColumn(serverFile -> {
            HorizontalLayout layout = new HorizontalLayout();
            layout.addClassNames("w-full");
            layout.setPadding(false);
            layout.setSpacing(false);
            layout.setAlignItems(FlexComponent.Alignment.CENTER);

            if (serverFile.isDirectory()) {
                layout.add(new Icon(VaadinIcon.FOLDER_O));
            }
            else {
                layout.add(new Icon(VaadinIcon.FILE_TEXT_O));
            }

            Span span = new Span(serverFile.getFileName());
            span.addClassNames("pl-s", "w-full");
            layout.add(span);

            return layout;
        });
    }

    private void rememberExpandAndCollapse() {
        addExpandListener(serverFileTreeGridExpandEvent -> expandedNodes.addAll(serverFileTreeGridExpandEvent.getItems()));
        addCollapseListener(serverFileTreeGridCollapseEvent -> expandedNodes.removeAll(serverFileTreeGridCollapseEvent.getItems()));
    }

    private void refreshFileTree(List<ServerFile> projectFiles, List<ServerFile> expandedFiles) {
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

    public void deleteSelectedFile() {
        ServerFile parent = selectedFile.getParent();
        parent.getChildren().remove(selectedFile);
        selectedFile = null;

        refreshFileTree(projectFiles, expandedNodes);
    }

    public ServerFile getSelectedFile() { return selectedFile; }
}

package com.company.application.views.projectdocuments;

import com.company.application.domain.projectdocuments.data.ServerFile;
import com.company.application.domain.projectdocuments.usecase.CreateFileTreeUseCase;
import com.company.application.domain.projectdocuments.usecase.EditFileUseCase;
import com.company.application.views.core.mainlayout.MainLayout;
import com.company.application.views.projectdocuments.component.EmbeddedPdfDocument;
import com.company.application.views.projectdocuments.component.FileTree;
import com.company.application.views.projectdocuments.component.TextArea;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;
import java.util.List;

/**
 * @author Thorsten Zieres, 1297197
 */
@PageTitle("Unterlagen")
@Route(value = "editor", layout = MainLayout.class)
public class ProjectDocumentsView extends Div {

    private final SplitLayout mainLayout;
    private ServerFile selectedFile;
    private final List<ServerFile> projectFiles;
    private final EditFileUseCase editFileUseCase;

    public ProjectDocumentsView(CreateFileTreeUseCase fileTreeUseCase, EditFileUseCase editFileUseCase) {
        this.projectFiles = fileTreeUseCase.getProjectFiles();
        this.editFileUseCase = editFileUseCase;
        addClassNames("flex", "flex-grow", "h-full");
        setHeight("100%");

        mainLayout = new SplitLayout();
        mainLayout.setSizeFull();
        mainLayout.addToPrimary(getFileSelector());

        getTextArea(mainLayout);

        add(mainLayout);
    }

    private void getTextArea(SplitLayout splitLayout) {

        if (selectedFile == null || selectedFile.isDirectory()) {
            splitLayout.addToSecondary(new Paragraph("Select something"));
        } else {

            if (selectedFile.getFileSuffix().equals("pdf")) {
                splitLayout.addToSecondary(new EmbeddedPdfDocument(selectedFile.getAbsolutePath()));
            } else {
                splitLayout.addToSecondary(new TextArea(editFileUseCase, selectedFile));
            }

        }
    }

    private void redrawTextArea(SplitLayout splitLayout) {
        if (splitLayout.getSecondaryComponent() != null)
            splitLayout.remove(splitLayout.getSecondaryComponent());

        getTextArea(splitLayout);
    }

    private Component getFileSelector() {
        Div div = new Div();
        div.addClassName("file-selector");

        FileTree fileTree = new FileTree(ServerFile::getFileName);
        fileTree.setItems(projectFiles, file -> {
            if (file.isDirectory()) {
                return file.getChildren();
            } else {
                return Collections.emptyList();
            }
        });

        fileTree.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                selectedFile = event.getValue();
                redrawTextArea(mainLayout);
            }
        });

        div.add(fileTree);

        return div;
    }
}

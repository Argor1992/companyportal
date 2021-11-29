package com.company.application.views.projectdocuments;

import com.company.application.domain.projectdocuments.data.ServerFile;
import com.company.application.domain.projectdocuments.usecase.CreateFileTreeUseCase;
import com.company.application.domain.projectdocuments.usecase.EditFileUseCase;
import com.company.application.views.core.mainlayout.MainLayout;
import com.company.application.views.projectdocuments.component.fileeditor.pdf.EmbeddedPdfDocument;
import com.company.application.views.projectdocuments.component.filepicker.FileTree;
import com.company.application.views.projectdocuments.component.fileeditor.text.TextArea;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Thorsten Zieres, 1297197
 */
@PageTitle("Unterlagen")
@Route(value = "editor", layout = MainLayout.class)
public class ProjectDocumentsView extends Div {

    private final SplitLayout mainLayout;
    private FileTree fileTree;
    private ServerFile selectedFile;
    private final List<ServerFile> projectFiles;
    private final List<ServerFile> expandedNodes;
    private final EditFileUseCase editFileUseCase;

    public ProjectDocumentsView(CreateFileTreeUseCase createFileTreeUseCase, EditFileUseCase editFileUseCase) {
        this.projectFiles = createFileTreeUseCase.getProjectFiles();
        this.editFileUseCase = editFileUseCase;
        this.expandedNodes = new ArrayList<>();
        addClassNames("flex", "flex-grow", "h-full");
        setHeight("100%");

        mainLayout = new SplitLayout();
        mainLayout.setSizeFull();

        if (!projectFiles.isEmpty()) {
            fileTree = new FileTree(projectFiles, expandedNodes);
            getFileSelector();
            getTextArea();
        } else {
            mainLayout.addToPrimary(new Paragraph("No current User found"));
            mainLayout.addToSecondary(new Paragraph("No selectable Files found"));
        }

        add(mainLayout);
    }

    private void getTextArea() {
        if (selectedFile == null || selectedFile.isDirectory()) {
            mainLayout.addToSecondary(new Paragraph("Select something"));
        } else {

            if (selectedFile.getFileSuffix().equals("pdf")) {
                mainLayout.addToSecondary(new EmbeddedPdfDocument(selectedFile.getAbsolutePath()));
            } else {
                mainLayout.addToSecondary(new TextArea(this));
            }

        }
    }

    private void redrawTextArea() {
        if (mainLayout.getSecondaryComponent() != null)
            mainLayout.remove(mainLayout.getSecondaryComponent());

        getTextArea();
    }

    private void getFileSelector() {
        Div div = new Div();
        div.addClassName("file-selector");

        fileTree.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                selectedFile = event.getValue();
                redrawTextArea();
            }
        });

        fileTree.addExpandListener(serverFileTreeGridExpandEvent -> {
            Collection<ServerFile> items = serverFileTreeGridExpandEvent.getItems();
            if (!items.isEmpty()) {
                expandedNodes.addAll(items);
            }
        });

        fileTree.addCollapseListener(serverFileTreeGridCollapseEvent -> {
            Collection<ServerFile> items = serverFileTreeGridCollapseEvent.getItems();
            if (!items.isEmpty()) {
                expandedNodes.removeAll(items);
            }
        });

        div.add(fileTree);

        mainLayout.addToPrimary(div);
    }

    public void deleteFile() {
        ServerFile parent = selectedFile.getParent();
        parent.getChildren().remove(selectedFile);
        selectedFile = null;

        redrawTextArea();
        fileTree.refreshFileTree(projectFiles, expandedNodes);
    }

    public ServerFile getSelectedFile() { return selectedFile; }
    public void setSelectedFile(ServerFile selectedFile) { this.selectedFile = selectedFile; }
    public EditFileUseCase getEditFileUseCase() { return editFileUseCase; }
}

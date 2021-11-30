package com.company.application.views.projectdocuments;

import com.company.application.domain.projectdocuments.data.ServerFile;
import com.company.application.domain.projectdocuments.usecase.CreateFileTreeUseCase;
import com.company.application.domain.projectdocuments.usecase.EditFileUseCase;
import com.company.application.views.core.mainlayout.MainLayout;
import com.company.application.views.projectdocuments.component.fileeditor.pdf.EmbeddedPdfDocument;
import com.company.application.views.projectdocuments.component.fileeditor.text.TextArea;
import com.company.application.views.projectdocuments.component.filepicker.FileTree;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;

/**
 * @author Thorsten Zieres, 1297197
 */
@PageTitle("Unterlagen")
@Route(value = "editor", layout = MainLayout.class)
public class ProjectDocumentsView extends Div {

    private final SplitLayout mainLayout;
    private FileTree fileTree;
    private final EditFileUseCase editFileUseCase;

    public ProjectDocumentsView(CreateFileTreeUseCase createFileTreeUseCase, EditFileUseCase editFileUseCase) {
        List<ServerFile> projectFiles = createFileTreeUseCase.getProjectFiles();
        this.editFileUseCase = editFileUseCase;

        addClassNames("flex", "flex-grow", "h-full");
        setHeight("100%");

        mainLayout = new SplitLayout();
        mainLayout.setSizeFull();

        if (!projectFiles.isEmpty()) {
            fileTree = new FileTree(this, projectFiles);

            Div div = new Div();
            div.addClassName("file-selector");
            div.add(fileTree);
            mainLayout.addToPrimary(div);

            getTextArea();
        } else {
            mainLayout.addToPrimary(new Paragraph("No current User found"));
            mainLayout.addToSecondary(new Paragraph("No selectable Files found"));
        }

        add(mainLayout);
    }

    private void getTextArea() {
        if (fileTree.getSelectedFile() == null || fileTree.getSelectedFile().isDirectory()) {
            mainLayout.addToSecondary(new Paragraph("Select something"));
        } else {

            if (fileTree.getSelectedFile().getFileSuffix().equals("pdf")) {
                mainLayout.addToSecondary(new EmbeddedPdfDocument(fileTree.getSelectedFile().getAbsolutePath()));
            } else {
                mainLayout.addToSecondary(new TextArea(this));
            }

        }
    }

    public void redrawTextArea() {
        if (mainLayout.getSecondaryComponent() != null)
            mainLayout.remove(mainLayout.getSecondaryComponent());

        getTextArea();
    }

    public void deleteSelectedFile() {
        fileTree.deleteSelectedFile();
        redrawTextArea();
    }

    public ServerFile getSelectedFile() { return fileTree.getSelectedFile(); }
    public EditFileUseCase getEditFileUseCase() { return editFileUseCase; }
}

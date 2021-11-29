package com.company.application.views.projectdocuments.component.fileeditor.text;

import com.company.application.domain.projectdocuments.data.ServerFile;
import com.company.application.domain.projectdocuments.usecase.EditFileUseCase;
import com.company.application.views.projectdocuments.ProjectDocumentsView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamResource;
import org.vaadin.olli.FileDownloadWrapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TextArea extends VerticalLayout {

    private final Button editButton;
    private final Button saveButton;
    private final Button refreshButton;
    private final Button shareButton;
    private final Button downloadButton;
    private final Button deleteButton;
    private final TextEditor textEditor;
    private final ProjectDocumentsView projectDocumentsView;
    private final ServerFile selectedFile;
    private final EditFileUseCase editFileUseCase;

    public TextArea(ProjectDocumentsView projectDocumentsView) {
        this.projectDocumentsView = projectDocumentsView;
        this.selectedFile = projectDocumentsView.getSelectedFile();
        this.editFileUseCase = projectDocumentsView.getEditFileUseCase();

        setPadding(false);
        setSpacing(false);

        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.setPadding(false);
        toolbar.setSpacing(false);
        toolbar.addClassName("toolbar");

        textEditor = new TextEditor(projectDocumentsView.getSelectedFile().getAbsolutePath(), false);
        editButton = new Button(new Icon(VaadinIcon.EDIT));
        saveButton = new Button(new Icon(VaadinIcon.FILE_O));
        refreshButton = new Button(new Icon(VaadinIcon.FILE_REFRESH));
        shareButton = new Button(new Icon(VaadinIcon.SHARE));
        downloadButton = new Button(new Icon(VaadinIcon.DOWNLOAD));
        deleteButton = new Button(new Icon(VaadinIcon.TRASH));

        toolbar.add(initEditButton(), initSaveButton(), initRefreshButton(), initShareButton(), initDownloadButton(), initDeleteButton());
        add(toolbar, textEditor);
    }

    private Component initEditButton() {
        editButton.addClassNames("toolbar-button", "clickable");
        editButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        editButton.addClickListener(buttonClickEvent -> {
            if (editFileUseCase.isAllowedToEdit()) {
                textEditor.setEnabled();
                saveButton.setEnabled(true);
                saveButton.addClassName("clickable");
            } else {
                new AcceptPopup("Nur Admins oder Entwickler des Projeks sind berechtigt Dateien zu verändern.");
            }
        });

        return editButton;
    }

    private Component initSaveButton() {
        saveButton.addClassNames("toolbar-button");
        saveButton.setEnabled(false);
        saveButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        saveButton.addClickListener(buttonClickEvent -> {
            if (editFileUseCase.storeFile(selectedFile, textEditor.getValue())) {
                Notification.show(
                        "Datei erfolgreich gespeichert", 3000,
                        Notification.Position.BOTTOM_START);
            } else {
                Notification.show(
                        "Something went wrong while storing!", 3000,
                        Notification.Position.BOTTOM_START);
            }
        });

        return saveButton;
    }

    private Component initShareButton() {
        shareButton.addClassNames("toolbar-button", "clickable");
        shareButton.addClickListener(buttonClickEvent -> Notification.show(
                "Dieses Feature ist für dieses Projekt nicht freigegeben", 3000,
                Notification.Position.BOTTOM_START));
        shareButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        return shareButton;
    }

    private Component initRefreshButton() {
        refreshButton.addClassNames("toolbar-button", "clickable");
        refreshButton.addClickListener(buttonClickEvent -> Notification.show(
                "Datei erfolgreich aktualisiert", 3000,
                Notification.Position.BOTTOM_START));
        refreshButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        return refreshButton;
    }

    private Component initDownloadButton() {
        downloadButton.addClassNames("toolbar-button", "clickable");
        downloadButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        if (editFileUseCase.isAllowedToEdit()) {
            StreamResource resource = new StreamResource(selectedFile.getFileName(), () -> {
                try {
                    return new FileInputStream(selectedFile.getAbsolutePath());
                } catch (FileNotFoundException e) {
                    return null;
                }
            });

            FileDownloadWrapper downloadWrapper = new FileDownloadWrapper(resource);
            downloadWrapper.wrapComponent(downloadButton);

            return downloadWrapper;
        } else {
            downloadButton.addClickListener(buttonClickEvent -> new AcceptPopup("Nur Admins oder Entwickler des Projeks sind berechtigt Sourcecode herunterzuladen."));
            return downloadButton;
        }
    }

    private Component initDeleteButton() {
        deleteButton.addClassNames("toolbar-button", "pr-l", "clickable");
        deleteButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        deleteButton.addClickListener(buttonClickEvent -> {
            if (editFileUseCase.isAllowedToEdit()) {
                new DeleteFilePopup(selectedFile, editFileUseCase, projectDocumentsView);
            } else {
                new AcceptPopup("Nur Admins oder Entwickler des Projeks sind berechtigt Dateien zu löschen.");
            }
        });

        return deleteButton;
    }
}

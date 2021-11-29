package com.company.application.views.projectdocuments.component;

import com.company.application.domain.projectdocuments.data.ServerFile;
import com.company.application.domain.projectdocuments.usecase.EditFileUseCase;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TextArea extends VerticalLayout {

    private final Button edit;
    private final Button save;
    private final Button refresh;
    private final Button share;
    private final Button download;
    private final Button delete;
    private final TextEditor textEditor;
    private final EditFileUseCase editFileUseCase;

    public TextArea(EditFileUseCase editFileUseCase, ServerFile selectedFile) {
        this.editFileUseCase = editFileUseCase;
        setPadding(false);
        setSpacing(false);

        HorizontalLayout toolbar = new HorizontalLayout();
        toolbar.setPadding(false);
        toolbar.setSpacing(false);
        toolbar.addClassName("toolbar");

        textEditor = new TextEditor(selectedFile.getAbsolutePath(), false);
        edit = new Button(new Icon(VaadinIcon.EDIT));
        save = new Button(new Icon(VaadinIcon.FILE_O));
        refresh = new Button(new Icon(VaadinIcon.FILE_REFRESH));
        share = new Button(new Icon(VaadinIcon.SHARE));
        download = new Button(new Icon(VaadinIcon.DOWNLOAD));
        delete = new Button(new Icon(VaadinIcon.TRASH));

        initEditButton(editFileUseCase);
        initSaveButton(editFileUseCase, selectedFile);
        initRefreshButton();
        initShareButton();
        initDownloadButton();
        initDeleteButton();

        toolbar.add(edit, save, refresh, share, download, delete);
        add(toolbar, textEditor);
    }

    private void initEditButton(EditFileUseCase editFileUseCase) {
        edit.addClassNames("toolbar-button", "clickable");
        edit.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        edit.addClickListener(buttonClickEvent -> {
            if (editFileUseCase.isAllowedToEdit()) {
                textEditor.setEnabled();
                save.setEnabled(true);
                save.addClassName("clickable");
            } else {
                openAcceptPopup("Nur Admins oder Entwickler des Projeks sind berechtigt Dateien zu verändern.");
            }
        });
    }

    private void initSaveButton(EditFileUseCase editFileUseCase, ServerFile selectedFile) {
        save.addClassNames("toolbar-button");
        save.setEnabled(false);
        save.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addClickListener(buttonClickEvent -> {
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
    }

    private void initShareButton() {
        share.addClassNames("toolbar-button", "clickable");
        share.addClickListener(buttonClickEvent -> Notification.show(
                "Dieses Feature ist für dieses Projekt nicht freigegeben", 3000,
                Notification.Position.BOTTOM_START));
        share.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    }

    private void initRefreshButton() {
        refresh.addClassNames("toolbar-button", "clickable");
        refresh.addClickListener(buttonClickEvent -> Notification.show(
                "Datei erfolgreich aktualisiert", 3000,
                Notification.Position.BOTTOM_START));
        refresh.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    }

    private void initDownloadButton() {
        download.addClassNames("toolbar-button", "clickable");
        download.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        download.addClickListener(buttonClickEvent -> {
            if (editFileUseCase.isAllowedToEdit()) {
                System.err.println("Download");
            } else {
                openAcceptPopup("Nur Admins oder Entwickler des Projeks sind berechtigt Sourcecode herunterzuladen.");
            }
        });
    }

    private void initDeleteButton() {
        delete.addClassNames("toolbar-button", "pr-l", "clickable");
        delete.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        delete.addClickListener(buttonClickEvent -> {
            if (editFileUseCase.isAllowedToEdit()) {
                openDeletePopup();
            } else {
                openAcceptPopup("Nur Admins oder Entwickler des Projeks sind berechtigt Dateien zu löschen.");
            }
        });
    }

    private void openAcceptPopup(String text) {
        Dialog popup = new Dialog();

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);

        Span span = new Span(text);

        Button accept = new Button("Ok", VaadinIcon.CHECK.create(), e -> popup.close());
        accept.addClickShortcut(Key.ENTER);

        layout.add(span, accept);
        popup.add(layout);

        popup.open();
    }

    private void openDeletePopup() {
        Dialog popup = new Dialog();

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);

        Span span = new Span("Sind Sie sich sicher, dass Sie die Datei löschen möchten?");

        Button accept = new Button("Ok", VaadinIcon.CHECK.create(), e -> {
            System.err.println("Deleted");
            popup.close();
        });
        accept.addClickShortcut(Key.ENTER);

        Button cancel = new Button("Abbrechen", e -> popup.close());
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancel.getStyle().set("margin-left", "auto");

        HorizontalLayout buttons = new HorizontalLayout(accept, cancel);
        buttons.setWidthFull();
        buttons.setMargin(false);

        layout.add(span, buttons);
        popup.add(layout);

        popup.open();
    }

}

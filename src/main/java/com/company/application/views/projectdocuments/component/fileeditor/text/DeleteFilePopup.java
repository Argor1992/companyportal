package com.company.application.views.projectdocuments.component.fileeditor.text;

import com.company.application.domain.projectdocuments.data.ServerFile;
import com.company.application.domain.projectdocuments.usecase.EditFileUseCase;
import com.company.application.views.projectdocuments.ProjectDocumentsView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class DeleteFilePopup extends VerticalLayout {
    public DeleteFilePopup(ServerFile selectedFile, EditFileUseCase editFileUseCase, ProjectDocumentsView projectDocumentsView) {
        Dialog popup = new Dialog();

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);

        Span span = new Span("Sind Sie sich sicher, dass Sie die Datei löschen möchten?");
        span.addClassNames("popup-header");

        Button accept = new Button("Ok", VaadinIcon.CHECK.create(), e -> {
            popup.close();

            if (editFileUseCase.deleteFile(selectedFile)) {
                Notification.show(
                        "Datei erfolgreich gelöscht", 3000,
                        Notification.Position.BOTTOM_START);
                projectDocumentsView.deleteSelectedFile();
            } else {
                Notification.show(
                        "Datei konnte nicht gelöscht werden", 3000,
                        Notification.Position.BOTTOM_START);
            }

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

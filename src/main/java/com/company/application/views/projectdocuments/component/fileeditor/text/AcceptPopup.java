package com.company.application.views.projectdocuments.component.fileeditor.text;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class AcceptPopup extends VerticalLayout {
    public AcceptPopup(String text) {
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
}

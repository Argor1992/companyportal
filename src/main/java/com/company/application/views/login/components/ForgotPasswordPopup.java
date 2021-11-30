package com.company.application.views.login.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;

public class ForgotPasswordPopup extends VerticalLayout {
    private final Dialog popup;

    private final EmailField email = new EmailField("E-Mail-Adresse");

    public ForgotPasswordPopup() {
        popup = new Dialog(this);
        popup.setWidth("400px");
        setPadding(false);

        email.setErrorMessage("Bitte eine valide E-Mail-Adresse angeben");
        email.setWidthFull();

        Span header = new Span("Bitte Ihre E-Mail-Adresse eingeben");
        header.addClassNames("popup-header");

        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();
        layout.setMargin(false);
        layout.add(email);

        add(header, layout, getSendAndCancelButton());
        popup.open();
    }

    private Component getSendAndCancelButton() {
        Button sendButton = new Button("Senden", VaadinIcon.CHECK.create(), e -> {
            popup.close();
            Notification.show(
                    "Wir haben eine E-Mail mit dem neuen Passwort an ihre E-Mail-Adresse versendet",
                    3000,
                    Notification.Position.BOTTOM_START);
        });
        sendButton.addClickShortcut(Key.ENTER);


        Button cancel = new Button("Cancel", e -> popup.close());
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        cancel.getStyle().set("margin-left", "auto");

        HorizontalLayout buttons = new HorizontalLayout(sendButton, cancel);
        buttons.setWidthFull();
        buttons.setMargin(false);

        return buttons;
    }
}

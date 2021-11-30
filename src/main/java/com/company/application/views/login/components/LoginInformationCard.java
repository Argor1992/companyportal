package com.company.application.views.login.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class LoginInformationCard extends VerticalLayout {
    public LoginInformationCard() {
        addClassNames("card-layout");

        VerticalLayout card = new VerticalLayout();
        card.setSpacing(false);
        card.setPadding(false);
        card.addClassName("card");

        card.add(getForgotPassword(), getRegister());

        add(card);
    }

    private Component getForgotPassword() {
        VerticalLayout layout = new VerticalLayout();
        layout.addClassName("card-forgot-password");
        layout.setPadding(false);
        layout.setSpacing(false);
        Span header = new Span("Passwort Vergessen?");
        header.addClassName("card-header");
        Span description = new Span("Wenn Sie Ihr Passwort vergessen haben, können Sie hier ein neues anfordern.");
        description.addClassName("card-description");
        Span link = new Span(">> neues Password anfordern");
        link.addClassNames("card-link", "clickable");
        link.addClickListener(event -> new ForgotPasswordPopup());

        layout.add(header, description, link);
        return layout;
    }

    private Component getRegister() {
        VerticalLayout layout = new VerticalLayout();
        layout.addClassName("card-register");
        layout.setPadding(false);
        layout.setSpacing(false);
        Span header = new Span("Noch nicht registriert?");
        header.addClassName("card-header");
        Span description = new Span("Wenden Sie sich an einen Administrator, der Ihnen einen Account anlegen wird.");
        description.addClassName("card-description");

        layout.add(header, description);
        return layout;
    }
}

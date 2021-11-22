package com.company.application.views.core.components.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class CardRow extends HorizontalLayout {
    private String headerText;
    private String subtitle;
    private String descriptionText;
    private List<Icon> buttons;

    private CardRow(String navigationLink) {
        addClassNames("card-row", "info-card", "clickable", "items-center");
        setSpacing(false);
        getThemeList().add("spacing-s");
        addClickListener(horizontalLayoutClickEvent -> UI.getCurrent().navigate(navigationLink));
    }

    public CardRow(String navigationLink, String headerText, String subtitle, String descriptionText) {
        this(navigationLink);

        this.headerText = headerText;
        this.subtitle = subtitle;
        this.descriptionText = descriptionText;

        add(getContent());
    }

    public CardRow(String navigationLink, String picture, String headerText, String subtitle, List<Icon> buttons) {
        this(navigationLink);

        this.headerText = headerText;
        this.subtitle = subtitle;
        this.buttons = buttons;

        Image image = new Image();
        image.setSrc(picture);

        add(image, getContent());
    }

    private Component getContent() {
        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        if (buttons == null || buttons.isEmpty()) {
            Span email = new Span(descriptionText);
            email.addClassName("description");

            description.add(getHeader(), email);
        } else {
            description.add(getHeader(), getActions());
        }

        return description;
    }

    private Component getHeader() {
        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setPadding(false);
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span name = new Span(headerText);
        name.addClassName("name");

        Span email = new Span(subtitle);
        email.addClassName("description");

        header.add(name, email);

        return header;
    }

    private Component getActions() {
        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-s");

        buttons.forEach(icon -> {
            icon.addClassNames("icon", "clickable");
            actions.add(icon);
        });

        return actions;
    }
}

package com.company.application.views.core.components.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class Card extends VerticalLayout {
    public Card(String headerText, List<Component> infoRows) {
        addClassName("card-component");
        addClassNames("card", "rounded-l", "p-m");
        setSpacing(false);
        setPadding(false);

        H1 header = new H1(headerText + ":");
        header.addClassNames("m-0", "text-xl", "pt-0", "pb-s");

        add(header);
        infoRows.forEach(this::add);
    }
}

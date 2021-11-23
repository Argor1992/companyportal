package com.company.application.views.core.components.card;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

/**
 * @author Thorsten Zieres, 1297197
 */
public class Card extends VerticalLayout {
    public Card(String headerText, List<Component> infoRows) {
        addClassNames("card-component", "card");
        setSpacing(false);
        setPadding(false);

        H1 header = new H1(headerText + ":");
        header.addClassNames("card-header");

        add(header);
        infoRows.forEach(this::add);
    }
}

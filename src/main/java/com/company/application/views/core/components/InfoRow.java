package com.company.application.views.core.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;

public class InfoRow extends Div {
    public InfoRow(String headerText, String description) {
        addClassNames("info-row");

        Span header = new Span(headerText + ":");
        header.addClassNames("info-header");

        Span badge = new Span(description);
        badge.addClassNames("info-description", "pr-0");

        add(header, badge);
    }
}

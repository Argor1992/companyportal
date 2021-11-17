package com.company.application.views.client;

import com.company.application.core.services.GermanTextService;
import com.company.application.domain.clientprofile.data.EmployeeRelationship;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class EmployeeCard extends VerticalLayout {

    private final GermanTextService dateService;

    public EmployeeCard(List<EmployeeRelationship> employees, GermanTextService dateService) {
        this.dateService = dateService;

        addClassName("card-component");
        addClassNames("card", "rounded-l", "p-m");
        setSpacing(false);
        setPadding(false);

        H1 header = new H1("KundenbetreuerInnen:");
        header.addClassNames("m-0", "text-xl", "pt-0", "pb-s");
        add(header);

        employees.forEach(employee -> add(createCard(employee)));
    }
    private HorizontalLayout createCard(EmployeeRelationship relation) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassNames("card-row", "info-card", "clickable");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");
        card.addClickListener(horizontalLayoutClickEvent ->
                UI.getCurrent().navigate("profile/" + relation.getContact().getId()));

        Image image = new Image();
        image.setSrc(relation.getContact().getProfilePicture());
        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span name = new Span(relation.getContact().getFirstName() + " " + relation.getContact().getLastName());
        name.addClassName("name");
        Span date = new Span("Seit: " + dateService.getGermanDate(relation.getSince()));
        date.addClassName("date");
        header.add(name, date);

        Span email = new Span(relation.getContact().getEmail());
        email.addClassName("description");

        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-s");

        Icon mail = VaadinIcon.ENVELOPE.create();
        mail.addClassNames("icon", "clickable");
        Icon call = VaadinIcon.PHONE.create();
        call.addClassNames("icon", "clickable");
        Icon delete = VaadinIcon.TRASH.create();
        delete.addClassNames("icon", "clickable");

        actions.add(mail, call, delete);

        description.add(header, email, actions);
        card.add(image, description);

        return card;
    }
}

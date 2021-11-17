package com.company.application.views.client;

import com.company.application.core.domain.IProject;
import com.company.application.core.services.GermanTextService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class ProjectCard extends VerticalLayout {
    private final GermanTextService dateService;

    public ProjectCard(List<IProject> projects, GermanTextService dateService) {
        this.dateService = dateService;

        addClassName("card-component");
        addClassNames("card", "rounded-l", "p-m");
        setSpacing(false);
        setPadding(false);

        H1 header = new H1("Projekte:");
        header.addClassNames("m-0", "text-xl", "pt-0", "pb-s");
        add(header);

        System.err.println("Here!!!!!!!!!!: " + projects.size());

        projects.forEach(project -> add(createCard(project)));
    }
    private HorizontalLayout createCard(IProject project) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassNames("card-row", "info-card", "clickable");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");
        card.addClickListener(horizontalLayoutClickEvent -> {
            UI.getCurrent().navigate("project/" + project.getId());
        });

        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span name = new Span(project.getName());
        name.addClassName("name");
        Span date = new Span("Seit: " + dateService.getGermanDate(project.getDate()));
        date.addClassName("date");
        header.add(name, date);

        Span email = new Span(project.getDescription());
        email.addClassName("description");

        description.add(header, email);
        card.add(description);

        return card;
    }
}

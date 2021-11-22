package com.company.application.views.project;

import com.company.application.core.services.GermanTextService;
import com.company.application.domain.projectprofile.data.Project;
import com.company.application.domain.projectprofile.usecase.ProjectUseCase;
import com.company.application.views.core.components.PageHeader;
import com.company.application.views.core.components.card.ClientCard;
import com.company.application.views.core.components.card.EmployeeCard;
import com.company.application.views.core.components.card.InformationCard;
import com.company.application.views.core.mainlayout.MainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@PageTitle("Projekt")
@Route(value = "project", layout = MainLayout.class)
public class ProjectView extends Main implements HasUrlParameter<Integer> {
    private final ProjectUseCase projectUseCase;
    private final GermanTextService germanTextService;

    private final VerticalLayout pageContent = new VerticalLayout();

    public ProjectView(ProjectUseCase projectUseCase, GermanTextService germanTextService) {
        this.projectUseCase = projectUseCase;
        this.germanTextService = germanTextService;

        addClassNames("justify-center");
        Div mainDiv = new Div();
        mainDiv.addClassNames("main-div", "pt-m");
        pageContent.setPadding(false);
        pageContent.setSpacing(false);
        pageContent.addClassName("card-component");

        mainDiv.add(pageContent);
        add(mainDiv);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {
        pageContent.removeAll();

        if (integer != null) {
            Optional<Project> project = projectUseCase.getProject(integer);

            if (project.isPresent()) {
                pageContent.add(
                        new PageHeader(project.get().getName(), project.get().getDescription()),
                        new InformationCard(project.get(), this.germanTextService),
                        new EmployeeCard(project.get().getProjectManagers()),
                        new ClientCard(project.get().getProjectClients()));
            } else {
                pageContent.add(new Paragraph("Error while fetching Project"));
            }

        } else {
            pageContent.add(new Paragraph("Project does not exits"));
        }
    }
}

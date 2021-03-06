package com.company.application.views.client;

import com.company.application.core.services.TextFormatService;
import com.company.application.domain.clientprofile.data.Client;
import com.company.application.domain.clientprofile.usecase.ClientUseCase;
import com.company.application.views.core.components.PageHeader;
import com.company.application.views.core.components.card.EmployeeCard;
import com.company.application.views.core.components.card.ProjectCard;
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

/**
 * @author Thorsten Zieres, 1297197
 */
@PageTitle("Kunde")
@Route(value = "client", layout = MainLayout.class)
public class ClientView extends Main implements HasUrlParameter<Integer> {
    private final ClientUseCase clientUseCase;
    private final TextFormatService textFormatService;

    private final VerticalLayout pageContent = new VerticalLayout();

    public ClientView(ClientUseCase clientUseCase, TextFormatService dateService) {
        this.clientUseCase = clientUseCase;
        this.textFormatService = dateService;

        addClassNames("justify-center");
        Div mainDiv = new Div();
        mainDiv.addClassNames("main-div");
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
            Optional<Client> client = clientUseCase.getClient(integer);

            if (client.isPresent()) {
                pageContent.add(
                        new PageHeader(client.get().getName(), client.get().getEmail()),
                        new ContactCard(client.get(), client.get().getAddress()),
                        new EmployeeCard(client.get().getContactPersons(), textFormatService),
                        new ProjectCard(client.get().getProjects(), textFormatService));
            } else {
                pageContent.add(new Paragraph("Error while fetching client"));
            }

        } else {
            pageContent.add(new Paragraph("Client does not exits"));
        }
    }
}

package com.company.application.views.client;

import com.company.application.domain.clientprofile.data.Client;
import com.company.application.domain.clientprofile.usecase.ClientUseCase;
import com.company.application.views.mainlayout.MainLayout;
import com.company.application.views.core.components.InfoRow;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@PageTitle("Kunde")
@Route(value = "client", layout = MainLayout.class)
public class ClientView extends Main implements HasUrlParameter<Integer> {
    private final ClientUseCase clientUseCase;
    private Client client;

    private final VerticalLayout pageContent = new VerticalLayout();

    public ClientView(ClientUseCase clientUseCase) {
        this.clientUseCase = clientUseCase;

        addClassNames("justify-center");
        Div mainDiv = new Div();
        mainDiv.addClassNames("profile-main-div", "pt-m");
        pageContent.setPadding(false);
        pageContent.setSpacing(false);

        mainDiv.add(pageContent);
        add(mainDiv);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {
        pageContent.removeAll();
        Optional<Client> client;
        if (integer != null) {
            client = clientUseCase.getClient(integer);

            if (client.isPresent()) {
                this.client = client.get();
                pageContent.add(getProfileHeader(), getMainContent());
            }

        } else {
            pageContent.add(new Paragraph("Client does not exits"));
        }
    }

    public Component getProfileHeader() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addClassNames("items-center", "h-auto", "flex", "pb-m");
        layout.add(getNameAndEmail());
        return layout;
    }

    private Component getNameAndEmail() {
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(false);

        layout.addClassNames("content-between");
        H1 name = new H1(client.getName());
        name.addClassNames("mb-0", "text-xl", "pt-0");
        Paragraph email = new Paragraph(client.getEmail());
        email.addClassNames("mt-0", "text-l", "text-secondary");

        layout.add(name, email);
        return layout;
    }

    private Component getMainContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.addClassName("card-list-view");
        layout.add(
                new ContactCard(client, client.getAddress()));
        return layout;
    }
}
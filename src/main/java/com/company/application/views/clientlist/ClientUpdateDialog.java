package com.company.application.views.clientlist;

import com.company.application.domain.clientlist.data.ClientOverview;
import com.company.application.domain.clientlist.usecase.ClientListUseCase;
import com.company.application.domain.clientlist.usecase.UpdateClientUseCase;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;

import java.util.Optional;

public class ClientUpdateDialog extends Div {
    private TextField name;
    private TextField representative;
    private TextField email;
    private TextField phone;

    private final Button cancel = new Button("Abbrechen");
    private final Button save = new Button("Speichern");

    private final BeanValidationBinder<ClientOverview> binder;

    private ClientOverview clientOverview;
    private final Grid<ClientOverview> grid;
    private final ClientListUseCase clientListUseCase;
    private final UpdateClientUseCase updateClientUseCase;

    public ClientUpdateDialog(Grid<ClientOverview> grid, ClientListUseCase clientListUseCase, UpdateClientUseCase updateClientUseCase) {
        this.grid = grid;
        this.clientListUseCase = clientListUseCase;
        this.updateClientUseCase = updateClientUseCase;
        binder = new BeanValidationBinder<>(ClientOverview.class);

        setClassName("flex flex-col");
        setWidth("400px");

        Div editorDiv = new Div();
        editorDiv.setClassName("p-l flex-grow");
        add(editorDiv);
        editorDiv.add(createFormLayout());
        add(createButtonLayout());

        initializeForm();
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        name = new TextField("First Name");
        representative = new TextField("Last Name");
        email = new TextField("Email");
        phone = new TextField("Phone");
        Component[] fields = new Component[]{name, representative, email, phone};

        for (Component field : fields) {
            ((HasStyle) field).addClassName("full-width");
        }
        formLayout.add(fields);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("w-full flex-wrap bg-contrast-5 py-s px-l");
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        return buttonLayout;
    }

    private void initializeForm() {
        binder.bindInstanceFields(this);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        // when a row is selected or deselected, populate form
        this.grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Optional<ClientOverview> client = this.clientListUseCase.getClient(event.getValue().getId());
                if (client.isPresent()) {
                    setEnabledOfForm(true);
                    populateForm(client.get());
                } else {
                    Notification.show(
                            "Something went wrong while selecting the client", 3000,
                            Notification.Position.BOTTOM_START);
                }
            } else {
                clearForm();
                UI.getCurrent().navigate(ClientListView.class);
            }
        });

        save.addClickListener(e -> {
            try {
                binder.writeBean(this.clientOverview);

                updateClientUseCase.updateClient(this.clientOverview);
                clearForm();
                refreshGrid();
                Notification.show("Update erfolgreich gespeichert.");
                UI.getCurrent().navigate(ClientListView.class);
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the data.");
            }
        });

        setEnabledOfForm(false);
    }

    private void setEnabledOfForm(boolean enabled) {
        save.setEnabled(enabled);
        cancel.setEnabled(enabled);
        name.setEnabled(enabled);
        representative.setEnabled(enabled);
        email.setEnabled(enabled);
        phone.setEnabled(enabled);
    }

    private void clearForm() {
        setEnabledOfForm(false);
        populateForm(null);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.setItems(clientListUseCase.getClientList());
        grid.getDataProvider().refreshAll();
    }

    private void populateForm(ClientOverview value) {
        this.clientOverview = value;
        binder.readBean(this.clientOverview);
    }
}

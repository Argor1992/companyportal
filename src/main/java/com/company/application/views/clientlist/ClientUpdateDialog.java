package com.company.application.views.clientlist;

import com.company.application.domain.clientlist.data.ClientOverview;
import com.company.application.domain.clientlist.usecase.ClientListUseCase;
import com.company.application.domain.clientlist.usecase.UpdateClientUseCase;
import com.company.application.views.core.components.list.UpdateDialog;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;

public class ClientUpdateDialog extends UpdateDialog<ClientOverview> {
    private TextField name;
    private TextField representative;
    private TextField email;
    private TextField phone;

    public ClientUpdateDialog(Grid<ClientOverview> grid, ClientListUseCase clientListUseCase, UpdateClientUseCase updateClientUseCase) {
        super(ClientListView.class, grid, new BeanValidationBinder<>(ClientOverview.class), clientListUseCase, updateClientUseCase);
    }

    @Override
    public Component createFormLayout() {
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

    @Override
    public void bindInstanceFields() {
        binder.bindInstanceFields(this);
    }

    @Override
    public void setEnabledOfForm(boolean enabled) {
        save.setEnabled(enabled);
        cancel.setEnabled(enabled);
        name.setEnabled(enabled);
        representative.setEnabled(enabled);
        email.setEnabled(enabled);
        phone.setEnabled(enabled);
    }
}

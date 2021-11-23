package com.company.application.views.core.components.list;

import com.company.application.domain.core.data.IHasId;
import com.company.application.domain.core.usecase.IListUseCase;
import com.company.application.domain.core.usecase.IUpdateUseCase;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;

import java.util.Optional;

public abstract class UpdateDialog<T extends IHasId<Integer>> extends Div {
    protected final Button cancel = new Button("Abbrechen");
    protected final Button save = new Button("Speichern");

    protected final IListUseCase<T> listUseCase;
    protected final IUpdateUseCase<T> updateUseCase;

    private final Class<? extends Component> typeClass;
    protected final Grid<T> grid;
    protected final BeanValidationBinder<T> binder;
    protected T selected;

    public UpdateDialog(Class<? extends Component> listViewClass, Grid<T> grid, BeanValidationBinder<T> binder, IListUseCase<T> listUseCase, IUpdateUseCase<T> updateUseCase) {
        this.typeClass = listViewClass;
        this.grid = grid;
        this.binder = binder;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;

        setClassName("flex flex-col");
        setWidth("400px");

        Div editorDiv = new Div();
        editorDiv.setClassName("p-l flex-grow");
        add(editorDiv);
        editorDiv.add(createFormLayout());
        add(createButtonLayout());

        bindInstanceFields();
        gridSelector();

        setEnabledOfForm(false);
    }

    abstract public void bindInstanceFields();

    abstract public Component createFormLayout();

    abstract public void setEnabledOfForm(boolean enabled);

    public void gridSelector() {
        this.grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Optional<T> selectedRow = this.listUseCase.getObject(event.getValue().getId());
                if (selectedRow.isPresent()) {
                    setEnabledOfForm(true);
                    populateForm(selectedRow.get());
                } else {
                    Notification.show(
                            "Something went wrong while selecting the row", 3000,
                            Notification.Position.BOTTOM_START);
                }
            } else {
                clearForm();
                UI.getCurrent().navigate(typeClass);
            }
        });
    }

    public Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("w-full flex-wrap bg-contrast-5 py-s px-l");
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);

        initializeCancelButton();
        initializeSaveButton();
        return buttonLayout;
    }

    public void initializeCancelButton() {
        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });
    }

    public void initializeSaveButton() {
        save.addClickListener(e -> {
            try {
                binder.writeBean(this.selected);

                updateUseCase.update(this.selected);
                clearForm();
                refreshGrid();
                Notification.show("Update erfolgreich gespeichert.");
                UI.getCurrent().navigate(typeClass);
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the data.");
            }
        });
    }

    public void clearForm() {
        setEnabledOfForm(false);
        populateForm(null);
    }

    public void refreshGrid() {
        grid.select(null);
        grid.setItems(listUseCase.getList());
        grid.getDataProvider().refreshAll();
    }

    public void populateForm(T value) {
        this.selected = value;
        binder.readBean(this.selected);
    }
}

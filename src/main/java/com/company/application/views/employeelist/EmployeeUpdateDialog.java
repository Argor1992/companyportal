package com.company.application.views.employeelist;

import com.company.application.data.employee.entity.Occupation;
import com.company.application.domain.employeelist.data.EmployeeOverview;
import com.company.application.domain.employeelist.usecase.EmployeeListUseCase;
import com.company.application.domain.updateemployee.usecase.UpdateEmployeeUseCase;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.renderer.ComponentRenderer;

import java.util.Optional;

@SuppressWarnings("FieldCanBeLocal")
public class EmployeeUpdateDialog extends Div {
    private TextField firstName;
    private TextField lastName;
    private TextField email;
    private TextField phone;
    private DatePicker dateOfBirth;
    private Select<Occupation> occupation;

    private final Button cancel = new Button("Abbrechen");
    private final Button save = new Button("Speichern");

    private final BeanValidationBinder<EmployeeOverview> binder;

    private EmployeeOverview selectedEmployee;
    private final Grid<EmployeeOverview> grid;
    private final EmployeeListUseCase employeeListUseCase;
    private final UpdateEmployeeUseCase updateEmployeeUseCase;

    public EmployeeUpdateDialog(Grid<EmployeeOverview> grid, EmployeeListUseCase employeeListUseCase, UpdateEmployeeUseCase updateEmployeeUseCase) {
        this.grid = grid;
        this.employeeListUseCase = employeeListUseCase;
        this.updateEmployeeUseCase = updateEmployeeUseCase;
        binder = new BeanValidationBinder<>(EmployeeOverview.class);

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
        firstName = new TextField("First Name");
        lastName = new TextField("Last Name");
        email = new TextField("Email");
        phone = new TextField("Phone");
        dateOfBirth = new DatePicker("Date Of Birth");
        occupation = new Select<>();
        occupation.setLabel("Occupation");
        occupation.setItems(Occupation.values());
        occupation.setRenderer(new ComponentRenderer<>(option -> new Text(option.getUiText())));
        Component[] fields = new Component[]{firstName, lastName, email, phone, dateOfBirth, occupation};

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
                Optional<EmployeeOverview> employee = this.employeeListUseCase.getEmployee(event.getValue().getId());
                if (employee.isPresent()) {
                    populateForm(employee.get());
                } else {
                    Notification.show(
                            "Something went wrong while selecting the employee", 3000,
                            Notification.Position.BOTTOM_START);
                }
            } else {
                clearForm();
                UI.getCurrent().navigate(EmployeeListView.class);
            }
        });

        save.addClickListener(e -> {
            try {
                if (this.selectedEmployee == null) {
                    this.selectedEmployee = new EmployeeOverview();
                }
                binder.writeBean(this.selectedEmployee);

                updateEmployeeUseCase.updateEmployee(this.selectedEmployee);
                clearForm();
                refreshGrid();
                Notification.show("Update erfolgreich gespeichert.");
                UI.getCurrent().navigate(EmployeeListView.class);
            } catch (ValidationException validationException) {
                Notification.show("An exception happened while trying to store the data.");
            }
        });
    }

    private void clearForm() {
        populateForm(null);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.setItems(employeeListUseCase.getEmployeeList());
        grid.getDataProvider().refreshAll();
    }

    private void populateForm(EmployeeOverview value) {
        this.selectedEmployee = value;
        binder.readBean(this.selectedEmployee);
    }
}

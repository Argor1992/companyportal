package com.company.application.views.employeelist;

import com.company.application.core.services.GermanDateService;
import com.company.application.data.employee.entity.Occupation;
import com.company.application.domain.employeelist.data.EmployeeOverview;
import com.company.application.domain.employeelist.usecase.EmployeeListUseCase;
import com.company.application.domain.updateemployee.usecase.UpdateEmployeeUseCase;
import com.company.application.views.mainlayout.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@SuppressWarnings("FieldCanBeLocal")
@PageTitle("Mitarbeiterübersicht")
@Route(value = "employees/:samplePersonID?/:action?(edit)", layout = MainLayout.class)
@Uses(Icon.class)
public class EmployeeListView extends Div {

    private final Grid<EmployeeOverview> grid = new Grid<>(EmployeeOverview.class, false);

    private TextField firstName;
    private TextField lastName;
    private TextField email;
    private TextField phone;
    private DatePicker dateOfBirth;
    private Select<Occupation> occupation;

    private final Button cancel = new Button("Abbrechen");
    private final Button save = new Button("Speichern");

    private BeanValidationBinder<EmployeeOverview> binder;

    private EmployeeOverview selectedEmployee;

    private final EmployeeListUseCase employeeListUseCase;

    public EmployeeListView(EmployeeListUseCase employeeListUseCase,
                            UpdateEmployeeUseCase updateEmployeeUseCase,
                            GermanDateService dateService) {
        this.employeeListUseCase = employeeListUseCase;
        addClassNames("master-detail-view", "flex", "flex-col", "h-full");

        // Create UI
        if (this.employeeListUseCase.showUpdateMenu()) {
            SplitLayout splitLayout = new SplitLayout();
            splitLayout.setSizeFull();

            createGridLayout(splitLayout);
            createEditorLayout(splitLayout);

            add(splitLayout);

            // when a row is selected or deselected, populate form
            grid.asSingleSelect().addValueChangeListener(event -> {
                if (event.getValue() != null) {
                    Optional<EmployeeOverview> employee = employeeListUseCase.getEmployee(event.getValue().getId());
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

            binder = new BeanValidationBinder<>(EmployeeOverview.class);
            // Bind fields. This where you'd define e.g. validation rules

            binder.bindInstanceFields(this);

            cancel.addClickListener(e -> {
                clearForm();
                refreshGrid();
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

        } else {
            VerticalLayout wrapper = new VerticalLayout();
            wrapper.setWidthFull();
            wrapper.setHeightFull();
            wrapper.addClassNames("pl-l", "pr-l", "pb-l");
            wrapper.setId("grid-wrapper");
            wrapper.setWidthFull();
            wrapper.add(grid);
            add(wrapper);
        }

        // Configure Grid
        grid.addColumn(EmployeeOverview::getFirstName, "firstName").setHeader("Vorname").setAutoWidth(true);
        grid.addColumn(EmployeeOverview::getLastName, "lastName").setHeader("Nachname").setAutoWidth(true);
        grid.addColumn(EmployeeOverview::getEmail, "email").setHeader("E-Mail").setAutoWidth(true);
        grid.addColumn(EmployeeOverview::getPhone, "phone").setHeader("Durchwahl").setAutoWidth(true);
        grid.addColumn(
                (ValueProvider<EmployeeOverview, String>) employeeOverview ->
                        dateService.getGermanDate(employeeOverview.getDateOfBirth()), "dateOfBirth").setHeader("Geburtsdatum").setAutoWidth(true);
        grid.addColumn(
                (ValueProvider<EmployeeOverview, String>) employeeOverview ->
                        employeeOverview.getOccupation().getUiText(), "occupation").setHeader("Abteilung").setAutoWidth(true);

        grid.setItems(employeeListUseCase.getEmployeeList());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("flex flex-col");
        editorLayoutDiv.setWidth("400px");

        Div editorDiv = new Div();
        editorDiv.setClassName("p-l flex-grow");
        editorLayoutDiv.add(editorDiv);

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
        editorDiv.add(formLayout);
        createButtonLayout(editorLayoutDiv);

        splitLayout.addToSecondary(editorLayoutDiv);
    }

    private void createButtonLayout(Div editorLayoutDiv) {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setClassName("w-full flex-wrap bg-contrast-5 py-s px-l");
        buttonLayout.setSpacing(true);
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save, cancel);
        editorLayoutDiv.add(buttonLayout);
    }

    private void createGridLayout(SplitLayout splitLayout) {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        splitLayout.addToPrimary(wrapper);
        wrapper.add(grid);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.setItems(employeeListUseCase.getEmployeeList());
        grid.getDataProvider().refreshAll();
    }

    private void clearForm() {
        populateForm(null);
    }

    private void populateForm(EmployeeOverview value) {
        this.selectedEmployee = value;
        binder.readBean(this.selectedEmployee);
    }
}

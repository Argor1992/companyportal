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
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@PageTitle("Mitarbeiterübersicht")
@Route(value = "employees/:samplePersonID?/:action?(edit)", layout = MainLayout.class)
@Uses(Icon.class)
public class EmployeeListView extends Div implements BeforeEnterObserver {

    private final String SAMPLEPERSON_ID = "samplePersonID";
    private final String SAMPLEPERSON_EDIT_ROUTE_TEMPLATE = "employees/%d/edit";

    private Grid<EmployeeOverview> grid = new Grid<>(EmployeeOverview.class, false);

    private final Button cancel = new Button("Abbrecher");
    private final Button save = new Button("Speichern");

    private final BeanValidationBinder<EmployeeOverview> binder;

    private EmployeeOverview selectedEmployee;

    private final EmployeeListUseCase employeeListUseCase;

    public EmployeeListView(EmployeeListUseCase employeeListUseCase, UpdateEmployeeUseCase updateEmployeeUseCase, GermanDateService dateService) {
        this.employeeListUseCase = employeeListUseCase;
        addClassNames("master-detail-view", "flex", "flex-col", "h-full");

        // Create UI
        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        createGridLayout(splitLayout);
        createEditorLayout(splitLayout);

        add(splitLayout);

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

        // when a row is selected or deselected, populate form
        grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                UI.getCurrent().navigate(String.format(SAMPLEPERSON_EDIT_ROUTE_TEMPLATE, event.getValue().getId()));
            } else {
                clearForm();
                UI.getCurrent().navigate(EmployeeListView.class);
            }
        });

        // Configure Form
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
                Notification.show("Update erfolgreich.");
                UI.getCurrent().navigate(EmployeeListView.class);
            } catch (ValidationException validationException) {
                Notification.show("Something went wrong while storing the update.");
            }
        });

    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<Integer> employeeId = event.getRouteParameters().getInteger(SAMPLEPERSON_ID);
        if (employeeId.isPresent()) {
            Optional<EmployeeOverview> employee = employeeListUseCase.getEmployee(employeeId.get());
            if (employee.isPresent()) {
                populateForm(employee.get());
            } else {
                Notification.show(
                        String.format("The requested samplePerson was not found, ID = %d", employeeId.get()), 3000,
                        Notification.Position.BOTTOM_START);
                refreshGrid();
                event.forwardTo(EmployeeListView.class);
            }
        }
    }

    private void createEditorLayout(SplitLayout splitLayout) {
        Div editorLayoutDiv = new Div();
        editorLayoutDiv.setClassName("flex flex-col");
        editorLayoutDiv.setWidth("400px");

        Div editorDiv = new Div();
        editorDiv.setClassName("p-l flex-grow");
        editorLayoutDiv.add(editorDiv);

        FormLayout formLayout = new FormLayout();
        TextField firstName = new TextField("First Name");
        TextField lastName = new TextField("Last Name");
        TextField email = new TextField("Email");
        TextField phone = new TextField("Phone");
        DatePicker dateOfBirth = new DatePicker("Date Of Birth");
        Select<Occupation> occupation = new Select<>();
        occupation.setLabel("Occupation");
        occupation.setItems(Occupation.values());
        occupation.setRenderer(new ComponentRenderer<>(option -> new Text(option.getUiText())));
        Checkbox important = new Checkbox("Important");
        important.getStyle().set("padding-top", "var(--lumo-space-m)");
        Component[] fields = new Component[]{firstName, lastName, email, phone, dateOfBirth, occupation, important};

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

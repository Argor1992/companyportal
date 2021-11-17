package com.company.application.views.projectlist;

import com.company.application.data.project.entity.ProjectState;
import com.company.application.domain.projectlist.data.ProjectOverview;
import com.company.application.domain.projectlist.usecase.ProjectListUseCase;
import com.company.application.domain.projectlist.usecase.UpdateProjectUseCase;
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
import com.vaadin.flow.data.binder.*;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.ValueProvider;

import java.util.Optional;

public class ProjectUpdateDialog extends Div {
    private TextField name;
    private TextField amount;
    private DatePicker date;
    private Select<ProjectState> projectState;
    private Select<Integer> priority;

    private final Button cancel = new Button("Abbrechen");
    private final Button save = new Button("Speichern");

    private final BeanValidationBinder<ProjectOverview> binder;

    private ProjectOverview selectedProject;
    private final Grid<ProjectOverview> grid;
    private final ProjectListUseCase projectListUseCase;
    private final UpdateProjectUseCase updateProjectUseCase;

    public ProjectUpdateDialog(Grid<ProjectOverview> grid, ProjectListUseCase projectListUseCase, UpdateProjectUseCase updateProjectUseCase) {
        this.grid = grid;
        this.projectListUseCase = projectListUseCase;
        this.updateProjectUseCase = updateProjectUseCase;
        binder = new BeanValidationBinder<>(ProjectOverview.class);

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
        name = new TextField("Name");
        amount = new TextField("Betrag");
        date = new DatePicker("Start");
        projectState = new Select<>();
        projectState.setLabel("Status");
        projectState.setItems(ProjectState.values());
        projectState.setRenderer(new ComponentRenderer<>(option -> new Text(option.getUiText())));

        priority = new Select<>();
        priority.setLabel("Priorität");
        priority.setItems(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Component[] fields = new Component[]{name, amount, date, projectState, priority};

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
        binder.forField(name).bind(ProjectOverview::getName, ProjectOverview::setName);
        binder.forField(amount)
                .withValidator((Validator<String>) (s, valueContext) -> {
                    try {
                        Double.parseDouble(s);
                        return ValidationResult.ok();
                    } catch (NumberFormatException e) {
                        return ValidationResult.error("Keine valide Zahl");
                    }
                }).bind(
                (ValueProvider<ProjectOverview, String>) project -> String.format("%.2f", project.getAmount()),
                (Setter<ProjectOverview, String>) (projectOverview, s) -> projectOverview.setAmount(Double.parseDouble(s))
        );
        binder.forField(date).bind(ProjectOverview::getDate, ProjectOverview::setDate);

        binder.forField(projectState).bind(ProjectOverview::getProjectState, ProjectOverview::setProjectState);
        binder.forField(priority).bind(ProjectOverview::getPriority, ProjectOverview::setPriority);

        cancel.addClickListener(e -> {
            clearForm();
            refreshGrid();
        });

        // when a row is selected or deselected, populate form
        this.grid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() != null) {
                Optional<ProjectOverview> project = this.projectListUseCase.getProject(event.getValue().getId());
                if (project.isPresent()) {
                    setEnabledOfForm(true);
                    populateForm(project.get());
                } else {
                    Notification.show(
                            "Something went wrong while selecting the project", 3000,
                            Notification.Position.BOTTOM_START);
                }
            } else {
                clearForm();
                UI.getCurrent().navigate(ProjectListView.class);
            }
        });

        save.addClickListener(e -> {
            try {
                binder.writeBean(this.selectedProject);

                updateProjectUseCase.updateProject(this.selectedProject);
                clearForm();
                refreshGrid();
                Notification.show("Update erfolgreich gespeichert.");
                UI.getCurrent().navigate(ProjectListView.class);
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
        amount.setEnabled(enabled);
        date.setEnabled(enabled);
        projectState.setEnabled(enabled);
        priority.setEnabled(enabled);
    }

    private void clearForm() {
        setEnabledOfForm(false);
        populateForm(null);
    }

    private void refreshGrid() {
        grid.select(null);
        grid.setItems(projectListUseCase.getProjectList());
        grid.getDataProvider().refreshAll();
    }

    private void populateForm(ProjectOverview value) {
        this.selectedProject = value;
        binder.readBean(this.selectedProject);
    }
}

package com.company.application.views.projectlist;

import com.company.application.data.project.entity.ProjectState;
import com.company.application.domain.projectlist.data.ProjectOverview;
import com.company.application.domain.projectlist.usecase.ProjectListUseCase;
import com.company.application.domain.projectlist.usecase.UpdateProjectUseCase;
import com.company.application.views.core.components.list.UpdateDialog;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.function.ValueProvider;

public class ProjectUpdateDialog extends UpdateDialog<ProjectOverview> {
    private TextField name;
    private TextField amount;
    private DatePicker date;
    private Select<ProjectState> projectState;
    private Select<Integer> priority;

    public ProjectUpdateDialog(Grid<ProjectOverview> grid, ProjectListUseCase projectListUseCase, UpdateProjectUseCase updateProjectUseCase) {
        super(ProjectListView.class, grid, new BeanValidationBinder<>(ProjectOverview.class), projectListUseCase, updateProjectUseCase);
    }

    @Override
    public Component createFormLayout() {
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

    @Override
    public void bindInstanceFields() {
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
    }

    @Override
    public void setEnabledOfForm(boolean enabled) {
        save.setEnabled(enabled);
        cancel.setEnabled(enabled);
        name.setEnabled(enabled);
        amount.setEnabled(enabled);
        date.setEnabled(enabled);
        projectState.setEnabled(enabled);
        priority.setEnabled(enabled);
    }
}

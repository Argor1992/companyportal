package com.company.application.views.employeelist;

import com.company.application.data.employee.entity.Occupation;
import com.company.application.domain.employeelist.data.EmployeeOverview;
import com.company.application.domain.employeelist.usecase.EmployeeListUseCase;
import com.company.application.domain.employeelist.usecase.UpdateEmployeeUseCase;
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
import com.vaadin.flow.data.renderer.ComponentRenderer;

/**
 * @author Thorsten Zieres, 1297197
 */
public class EmployeeUpdateDialog extends UpdateDialog<EmployeeOverview> {
    private TextField firstName;
    private TextField lastName;
    private TextField email;
    private TextField phone;
    private DatePicker dateOfBirth;
    private Select<Occupation> occupation;

    public EmployeeUpdateDialog(Grid<EmployeeOverview> grid, EmployeeListUseCase employeeListUseCase, UpdateEmployeeUseCase updateEmployeeUseCase) {
        super(EmployeeListView.class, grid, new BeanValidationBinder<>(EmployeeOverview.class), employeeListUseCase, updateEmployeeUseCase);
    }

    @Override
    public Component createFormLayout() {
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

    @Override
    public void bindInstanceFields() {
        binder.bindInstanceFields(this);
    }

    @Override
    public void setEnabledOfForm(boolean enabled) {
        save.setEnabled(enabled);
        cancel.setEnabled(enabled);
        firstName.setEnabled(enabled);
        lastName.setEnabled(enabled);
        email.setEnabled(enabled);
        phone.setEnabled(enabled);
        dateOfBirth.setEnabled(enabled);
        occupation.setEnabled(enabled);
    }
}

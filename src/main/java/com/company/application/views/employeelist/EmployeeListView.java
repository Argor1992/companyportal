package com.company.application.views.employeelist;

import com.company.application.core.services.TextFormatService;
import com.company.application.domain.employeelist.data.EmployeeOverview;
import com.company.application.domain.employeelist.usecase.EmployeeListUseCase;
import com.company.application.domain.employeelist.usecase.UpdateEmployeeUseCase;
import com.company.application.views.core.components.list.ListComponent;
import com.company.application.views.core.mainlayout.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * @author Thorsten Zieres, 1297197
 */
@PageTitle("Mitarbeiterübersicht")
@Route(value = "employees", layout = MainLayout.class)
@Uses(Icon.class)
public class EmployeeListView extends Div {

    private final Grid<EmployeeOverview> grid = new Grid<>(EmployeeOverview.class, false);

    private final EmployeeListUseCase employeeListUseCase;
    private final TextFormatService textFormatService;

    public EmployeeListView(EmployeeListUseCase employeeListUseCase,
                            UpdateEmployeeUseCase updateEmployeeUseCase,
                            TextFormatService textFormatService) {
        this.employeeListUseCase = employeeListUseCase;
        this.textFormatService = textFormatService;
        addClassNames("h-full");

        if (this.employeeListUseCase.showUpdateMenu()) {
            add(new ListComponent(grid, new EmployeeUpdateDialog(grid, employeeListUseCase, updateEmployeeUseCase)));
        } else {
            add(new ListComponent(grid));
        }

        initializeGrid();
    }

    private void initializeGrid() {
        grid.addColumn(EmployeeOverview::getFirstName, "firstName").setHeader("Vorname").setAutoWidth(true);
        grid.addColumn(EmployeeOverview::getLastName, "lastName").setHeader("Nachname").setAutoWidth(true);
        grid.addColumn(EmployeeOverview::getEmail, "email").setHeader("E-Mail").setAutoWidth(true);
        grid.addColumn(EmployeeOverview::getPhone, "phone").setHeader("Durchwahl").setAutoWidth(true);
        grid.addColumn(
                (ValueProvider<EmployeeOverview, String>) employeeOverview ->
                        textFormatService.getGermanDate(employeeOverview.getDateOfBirth()), "dateOfBirth").setHeader("Geburtsdatum").setAutoWidth(true);
        grid.addColumn(
                (ValueProvider<EmployeeOverview, String>) employeeOverview ->
                        employeeOverview.getOccupation().getUiText(), "occupation").setHeader("Abteilung").setAutoWidth(true);

        grid.setItems(employeeListUseCase.getList());
        grid.addThemeVariants(GridVariant.LUMO_COMPACT);
        grid.setHeightFull();

        grid.addItemClickListener(event -> {
            if (event.getClickCount() == 2)
                UI.getCurrent().navigate("profile/" + event.getItem().getId());
        });
    }
}

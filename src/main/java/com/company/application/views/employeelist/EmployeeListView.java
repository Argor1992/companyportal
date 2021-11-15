package com.company.application.views.employeelist;

import com.company.application.core.services.GermanDateService;
import com.company.application.domain.employeelist.data.EmployeeOverview;
import com.company.application.domain.employeelist.usecase.EmployeeListUseCase;
import com.company.application.domain.employeelist.usecase.UpdateEmployeeUseCase;
import com.company.application.views.mainlayout.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@SuppressWarnings("FieldCanBeLocal")
@PageTitle("Mitarbeiterübersicht")
@Route(value = "employees", layout = MainLayout.class)
@Uses(Icon.class)
public class EmployeeListView extends Div {

    private final Grid<EmployeeOverview> grid = new Grid<>(EmployeeOverview.class, false);

    private final EmployeeListUseCase employeeListUseCase;
    private final GermanDateService dateService;

    public EmployeeListView(EmployeeListUseCase employeeListUseCase,
                            UpdateEmployeeUseCase updateEmployeeUseCase,
                            GermanDateService dateService) {
        this.employeeListUseCase = employeeListUseCase;
        this.dateService = dateService;
        addClassNames("master-detail-view", "flex", "flex-col", "h-full");

        // Create UI
        if (this.employeeListUseCase.showUpdateMenu()) {
            SplitLayout splitLayout = new SplitLayout();
            splitLayout.setSizeFull();

            splitLayout.addToPrimary(createGridLayout());
            splitLayout.addToSecondary(new EmployeeUpdateDialog(grid, employeeListUseCase, updateEmployeeUseCase));
            add(splitLayout);

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
        initializeGrid();
    }

    private Component createGridLayout() {
        Div wrapper = new Div();
        wrapper.setId("grid-wrapper");
        wrapper.setWidthFull();
        wrapper.add(grid);
        return wrapper;
    }

    private void initializeGrid() {
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

        grid.addItemClickListener(event -> {
            if (event.getClickCount() == 2)
                UI.getCurrent().navigate("profile/" + event.getItem().getId());
        });
    }
}

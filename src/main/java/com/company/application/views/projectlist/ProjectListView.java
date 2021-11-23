package com.company.application.views.projectlist;

import com.company.application.core.services.GermanTextService;
import com.company.application.domain.projectlist.data.ProjectOverview;
import com.company.application.domain.projectlist.usecase.ProjectListUseCase;
import com.company.application.domain.projectlist.usecase.UpdateProjectUseCase;
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

@PageTitle("Projektübersicht")
@Route(value = "projects", layout = MainLayout.class)
@Uses(Icon.class)
public class ProjectListView extends Div {

    private final Grid<ProjectOverview> grid = new Grid<>(ProjectOverview.class, false);

    private final ProjectListUseCase projectListUseCase;
    private final GermanTextService dateService;

    public ProjectListView(ProjectListUseCase projectListUseCase,
                           UpdateProjectUseCase updateProjectUseCase,
                           GermanTextService dateService) {
        this.projectListUseCase = projectListUseCase;
        this.dateService = dateService;
        addClassNames("h-full");

        if (this.projectListUseCase.showUpdateMenu()) {
            add(new ListComponent(grid, new ProjectUpdateDialog(grid, projectListUseCase, updateProjectUseCase)));
        } else {
            add(new ListComponent(grid));
        }

        initializeGrid();
    }

    private void initializeGrid() {
        grid.addColumn(ProjectOverview::getName, "name").setHeader("Name").setAutoWidth(true);
        grid.addColumn(
                (ValueProvider<ProjectOverview, String>) projectOverview ->
                        dateService.getGermanCurrency(projectOverview.getAmount()), "amout")
                .setHeader("Betrag").setAutoWidth(true);
        grid.addColumn(
                (ValueProvider<ProjectOverview, String>) projectOverview ->
                        dateService.getGermanDate(projectOverview.getDate()), "date")
                .setHeader("Start").setAutoWidth(true);
        grid.addColumn(
                (ValueProvider<ProjectOverview, String>) projectOverview ->
                        projectOverview.getProjectState().getUiText(), "projectState")
                .setHeader("Status").setAutoWidth(true);
        grid.addColumn(ProjectOverview::getPriority, "priority").setHeader("Priorität").setAutoWidth(true);

        grid.setItems(projectListUseCase.getList());
        grid.addThemeVariants(GridVariant.LUMO_COMPACT);
        grid.setHeightFull();

        grid.addItemClickListener(event -> {
            if (event.getClickCount() == 2)
                UI.getCurrent().navigate("project/" + event.getItem().getId());
        });
    }
}

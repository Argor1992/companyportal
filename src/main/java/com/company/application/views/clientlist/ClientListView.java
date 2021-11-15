package com.company.application.views.clientlist;

import com.company.application.domain.clientlist.data.ClientOverview;
import com.company.application.domain.clientlist.usecase.ClientListUseCase;
import com.company.application.domain.clientlist.usecase.UpdateClientUseCase;
import com.company.application.views.mainlayout.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Kundenübersicht")
@Route(value = "clients", layout = MainLayout.class)
public class ClientListView extends Div {

    private final Grid<ClientOverview> grid = new Grid<>(ClientOverview.class, false);

    private final ClientListUseCase clientListUseCase;

    public ClientListView(ClientListUseCase clientListUseCase,
                          UpdateClientUseCase updateClientUseCase) {
        this.clientListUseCase = clientListUseCase;
        addClassNames("master-detail-view", "flex", "flex-col", "h-full");

        // Create UI
        if (this.clientListUseCase.showUpdateMenu()) {
            SplitLayout splitLayout = new SplitLayout();
            splitLayout.setSizeFull();

            splitLayout.addToPrimary(createGridLayout());
            splitLayout.addToSecondary(new ClientUpdateDialog(grid, clientListUseCase, updateClientUseCase));
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
        grid.addColumn(ClientOverview::getId, "id").setHeader("ID").setAutoWidth(true);
        grid.addColumn(ClientOverview::getName, "name").setHeader("Name").setAutoWidth(true);
        grid.addColumn(ClientOverview::getRepresentative, "representative").setHeader("Vertreter").setAutoWidth(true);
        grid.addColumn(ClientOverview::getEmail, "email").setHeader("E-Mail").setAutoWidth(true);
        grid.addColumn(ClientOverview::getPhone, "phone").setHeader("Telefon").setAutoWidth(true);

        grid.setItems(clientListUseCase.getClientList());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setHeightFull();

        grid.addItemClickListener(event -> {
            if (event.getClickCount() == 2)
                UI.getCurrent().navigate("client/" + event.getItem().getId());
        });
    }
}

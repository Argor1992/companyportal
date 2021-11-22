package com.company.application.views.clientlist;

import com.company.application.domain.clientlist.data.ClientOverview;
import com.company.application.domain.clientlist.usecase.ClientListUseCase;
import com.company.application.domain.clientlist.usecase.UpdateClientUseCase;
import com.company.application.views.core.components.list.ListComponent;
import com.company.application.views.core.mainlayout.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
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

        if (this.clientListUseCase.showUpdateMenu()) {
            add(new ListComponent(grid, new ClientUpdateDialog(grid, clientListUseCase, updateClientUseCase)));
        } else {
            add(new ListComponent(grid));
        }

        initializeGrid();
    }

    private void initializeGrid() {
        grid.addColumn(ClientOverview::getId, "id").setHeader("ID").setAutoWidth(true);
        grid.addColumn(ClientOverview::getName, "name").setHeader("Name").setAutoWidth(true);
        grid.addColumn(ClientOverview::getRepresentative, "representative").setHeader("Vertreter").setAutoWidth(true);
        grid.addColumn(ClientOverview::getEmail, "email").setHeader("E-Mail").setAutoWidth(true);
        grid.addColumn(ClientOverview::getPhone, "phone").setHeader("Telefon").setAutoWidth(true);

        grid.setItems(clientListUseCase.getClientList());
        grid.addThemeVariants(GridVariant.LUMO_COMPACT);
        grid.setHeightFull();

        grid.addItemClickListener(event -> {
            if (event.getClickCount() == 2)
                UI.getCurrent().navigate("client/" + event.getItem().getId());
        });
    }
}

package com.company.application.views.core.components.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;

public class ListComponent extends Div {
    public ListComponent(Grid<?> grid) {
        addClassNames("master-detail-view", "flex", "flex-col", "h-full");

        VerticalLayout gridWrapper = new VerticalLayout();
        gridWrapper.setWidthFull();
        gridWrapper.setHeightFull();
        gridWrapper.addClassNames("pl-l", "pr-l", "pb-l");
        gridWrapper.setId("grid-wrapper");
        gridWrapper.setWidthFull();
        gridWrapper.add(grid);

        add(gridWrapper);
    }

    public ListComponent(Grid<?> grid, Component updateDialog) {
        addClassNames("master-detail-view", "flex", "flex-col", "h-full");

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();

        Div gridWrapper = new Div();
        gridWrapper.setId("grid-wrapper");
        gridWrapper.setWidthFull();
        gridWrapper.add(grid);

        splitLayout.addToPrimary(gridWrapper);
        splitLayout.addToSecondary(updateDialog);
        add(splitLayout);
    }
}

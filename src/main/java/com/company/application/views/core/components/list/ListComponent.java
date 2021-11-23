package com.company.application.views.core.components.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;

/**
 * @author Thorsten Zieres, 1297197
 */
public class ListComponent extends Div {
    public ListComponent(Grid<?> grid) {
        addClassNames("master-detail-view", "list-component");

        HorizontalLayout gridWrapper = new HorizontalLayout();
        gridWrapper.setSpacing(false);
        gridWrapper.setMargin(false);
        gridWrapper.setWidthFull();
        gridWrapper.setHeightFull();
        gridWrapper.setId("grid-wrapper");
        gridWrapper.setWidthFull();
        gridWrapper.add(grid);

        add(gridWrapper);
    }

    public ListComponent(Grid<?> grid, Component updateDialog) {
        addClassNames("master-detail-view", "list-component");

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

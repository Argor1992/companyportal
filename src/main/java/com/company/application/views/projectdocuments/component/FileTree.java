package com.company.application.views.projectdocuments.component;

import com.company.application.domain.projectdocuments.data.ServerFile;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.data.renderer.TemplateRenderer;
import com.vaadin.flow.function.SerializableComparator;
import com.vaadin.flow.function.ValueProvider;

public class FileTree extends TreeGrid<ServerFile> {
    public FileTree(ValueProvider<ServerFile, HorizontalLayout> valueProvider) {
        setVerticalScrollingEnabled(true);
        addComponentHierarchyColumn(valueProvider);
    }
}

package com.company.application.views.projectdocuments.component;

import com.company.application.domain.projectdocuments.data.ServerFile;
import com.vaadin.flow.component.treegrid.TreeGrid;
import com.vaadin.flow.function.ValueProvider;

public class FileTree extends TreeGrid<ServerFile> {
    public FileTree(ValueProvider<ServerFile, ?> valueProvider) {
        addHierarchyColumn(valueProvider);
    }
}

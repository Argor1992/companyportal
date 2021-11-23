package com.company.application.views.core.mainlayout;

import com.vaadin.flow.component.Component;

/**
 * @author Thorsten Zieres, 1297197
 */
public class MenuItemInfo {
    private final String text;
    private final String iconClass;
    private final Class<? extends Component> view;

    public MenuItemInfo(String text, String iconClass, Class<? extends Component> view) {
        this.text = text;
        this.iconClass = iconClass;
        this.view = view;
    }

    public String getText() {
        return text;
    }

    public String getIconClass() {
        return iconClass;
    }

    public Class<? extends Component> getView() {
        return view;
    }
}

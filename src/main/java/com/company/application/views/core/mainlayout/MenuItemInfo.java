package com.company.application.views.core.mainlayout;

import com.vaadin.flow.component.Component;

public class MenuItemInfo {
    private String text;
    private String iconClass;
    private Class<? extends Component> view;

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

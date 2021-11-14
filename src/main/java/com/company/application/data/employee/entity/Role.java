package com.company.application.data.employee.entity;

import com.vaadin.flow.component.Component;

public enum Role {
    USER, ADMIN;

    public String getUiText() {
        switch (this) {
            case USER:
                return "User";
            case ADMIN:
                return "Admin";
            default:
                return "";
        }
    }
}

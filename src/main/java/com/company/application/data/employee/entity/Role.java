package com.company.application.data.employee.entity;

/**
 * @author Thorsten Zieres, 1297197
 */
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

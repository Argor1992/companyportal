package com.company.application.data.employee.entity;

/**
 * @author Thorsten Zieres, 1297197
 */
public enum Occupation {
    DEVELOPER, HUMAN_RESOURCES, MANAGEMENT, SERVICE_DESK, MARKETING, SALES;

    public String getUiText() {
        switch (this) {
            case SALES:
                return "Sales";
            case DEVELOPER:
                return "Entwickler";
            case MARKETING:
                return "Marketing";
            case MANAGEMENT:
                return "Management";
            case SERVICE_DESK:
                return "Service Desk";
            case HUMAN_RESOURCES:
                return "Personalabteilung";
            default:
                return "";
        }
    }
}

package com.company.application.data.project.entity;

/**
 * @author Thorsten Zieres, 1297197
 */
public enum ProjectState {
    SUCCESS, PENDING, ERROR;

    public String getUiText() {
        switch (this) {
            case ERROR:
                return "Unterbrochen";
            case PENDING:
                return "Pausiert";
            case SUCCESS:
                return "Erfolgreich";
            default:
                return "";
        }
    }
}

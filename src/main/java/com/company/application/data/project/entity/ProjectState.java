package com.company.application.data.project.entity;

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

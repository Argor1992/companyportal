package com.company.application.core.domain;

import com.company.application.data.project.entity.ProjectState;

import java.time.LocalDate;

public interface IProject {
    int getId();
    String getName();
    String getDescription();
    double getAmount();
    LocalDate getDate();
    ProjectState getProjectState();
    int getPriority();
}

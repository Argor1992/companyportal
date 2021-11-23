package com.company.application.core.domain;

import com.company.application.data.project.entity.ProjectState;
import com.company.application.domain.core.data.IHasId;

import java.time.LocalDate;

public interface IProject extends IHasId<Integer> {
    String getName();
    String getDescription();
    double getAmount();
    LocalDate getDate();
    ProjectState getProjectState();
    int getPriority();
}

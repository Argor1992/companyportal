package com.company.application.domain.core.data;

import com.company.application.data.project.entity.ProjectState;

import java.time.LocalDate;

/**
 * @author Thorsten Zieres, 1297197
 */
public interface IProject extends IHasId<Integer> {
    String getName();
    String getDescription();
    double getAmount();
    LocalDate getDate();
    ProjectState getProjectState();
    int getPriority();
}

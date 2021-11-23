package com.company.application.views.core.components.card;

import com.company.application.domain.core.data.IProject;
import com.company.application.core.services.TextFormatService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Thorsten Zieres, 1297197
 */
public class ProjectCard extends Card {
    public ProjectCard(List<IProject> projects, TextFormatService dateService) {
        super(
                "Projekte",
                projects.stream().map(project -> new CardRow(
                        "project/" + project.getId(),
                        project.getName(),
                        "Seit: " + dateService.getGermanDate(project.getDate()),
                        project.getDescription()
                )).collect(Collectors.toList())
        );
    }
}

package com.company.application.views.core.components.card;

import com.company.application.core.domain.IProject;
import com.company.application.core.services.GermanTextService;

import java.util.List;
import java.util.stream.Collectors;

public class ProjectCard extends Card {
    public ProjectCard(List<IProject> projects, GermanTextService dateService) {
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

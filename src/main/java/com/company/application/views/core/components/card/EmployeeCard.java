package com.company.application.views.core.components.card;

import com.company.application.domain.core.data.IEmployee;
import com.company.application.core.services.TextFormatService;
import com.company.application.domain.clientprofile.data.EmployeeRelationship;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Thorsten Zieres, 1297197
 */
public class EmployeeCard extends Card {
    public EmployeeCard(List<IEmployee> employees) {
        super(
                "ProjektbetreuerInnen",
                employees.stream().map(employee -> new CardRow(
                        "profile/" + employee.getId(),
                        employee.getProfilePicture(),
                        employee.getFirstName() + " " + employee.getLastName(),
                        employee.getEmail(),
                        List.of(
                                VaadinIcon.ENVELOPE.create(),
                                VaadinIcon.PHONE.create(),
                                VaadinIcon.TRASH.create()
                        )
                )).collect(Collectors.toList())
        );
    }

    public EmployeeCard(List<EmployeeRelationship> employeesRelation, TextFormatService dateService) {
        super(
                "KundenbetreuerInnen",
                employeesRelation.stream().map(relation -> new CardRow(
                        "profile/" + relation.getContact().getId(),
                        relation.getContact().getProfilePicture(),
                        relation.getContact().getFirstName() + " " + relation.getContact().getLastName(),
                                "Seit: " + dateService.getGermanDate(relation.getSince()),
                        List.of(
                                VaadinIcon.ENVELOPE.create(),
                                VaadinIcon.PHONE.create(),
                                VaadinIcon.TRASH.create()
                        )
                )).collect(Collectors.toList())
        );
    }
}

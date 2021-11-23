package com.company.application.views.core.components.card;

import com.company.application.domain.core.data.IProject;
import com.company.application.core.services.TextFormatService;
import com.company.application.domain.employeeprofile.data.Employee;
import com.company.application.views.core.components.InfoRow;

import java.util.List;

/**
 * @author Thorsten Zieres, 1297197
 */
public class InformationCard extends Card {
    public InformationCard(IProject project, TextFormatService textService) {
        super("Allgemeines", List.of(
                new InfoRow("Startdatum", textService.getGermanDate(project.getDate())),
                new InfoRow("Preis", textService.getGermanCurrency(project.getAmount())),
                new InfoRow("Status", project.getProjectState().getUiText()),
                new InfoRow("Priorität", String.valueOf(project.getPriority()))
        ));
    }

    public InformationCard(Employee employee, TextFormatService textService) {
        super("Allgemeines", List.of(
                new InfoRow("Geburtsdatum", textService.getGermanDate(employee.getDateOfBirth())),
                new InfoRow("Abteilung", employee.getOccupation().getUiText()),
                new InfoRow("Durchwahl", employee.getPhone()),
                new InfoRow("Adresse", employee.getAddress().getUiText()),
                new InfoRow("Rolle", employee.getRole().getUiText())
        ));
    }
}

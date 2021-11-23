package com.company.application.views.employee;

import com.company.application.core.services.TextFormatService;
import com.company.application.domain.employeeprofile.data.ClientRelationship;
import com.company.application.domain.employeeprofile.data.Employee;
import com.company.application.domain.employeeprofile.usecase.EmployeeProfileUseCase;
import com.company.application.views.core.components.PageHeader;
import com.company.application.views.core.components.card.ClientCard;
import com.company.application.views.core.components.card.InformationCard;
import com.company.application.views.core.components.card.ProjectCard;
import com.company.application.views.core.mainlayout.MainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Thorsten Zieres, 1297197
 */
@PageTitle("Profil")
@Route(value = "profile", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class EmployeeView extends Main implements HasUrlParameter<Integer> {
    private final EmployeeProfileUseCase employeeProfileUseCase;
    private final TextFormatService textFormatService;

    private final VerticalLayout pageContent = new VerticalLayout();

    public EmployeeView(EmployeeProfileUseCase employeeProfileUseCase, TextFormatService textFormatService) {
        this.employeeProfileUseCase = employeeProfileUseCase;
        this.textFormatService = textFormatService;

        addClassNames("justify-center");
        Div mainDiv = new Div();
        mainDiv.addClassNames("main-div");
        pageContent.setPadding(true);
        pageContent.setSpacing(false);
        pageContent.addClassName("card-component");

        mainDiv.add(pageContent);
        add(mainDiv);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter Integer integer) {
        pageContent.removeAll();
        Optional<Employee> employee;

        if (integer == null) {
            employee = employeeProfileUseCase.getCurrentUser();
        } else {
            employee = employeeProfileUseCase.getUser(integer);
        }

        if (employee.isPresent()) {
            pageContent.add(new PageHeader(employee.get().getProfilePicture(), employee.get().getDisplayName(), employee.get().getEmail()),
                    new InformationCard(employee.get(), this.textFormatService),
                    new ClientCard(employee.get().getClients().stream().map(ClientRelationship::getContact).collect(Collectors.toList())),
                    new ProjectCard(employee.get().getProjects(), textFormatService));
        } else {
            pageContent.add(new Paragraph("Employee does not exits"));
        }
    }
}

package com.company.application.views.profile;

import com.company.application.core.services.GermanDateService;
import com.company.application.domain.employeeprofile.data.EmployeeProfile;
import com.company.application.domain.employeeprofile.usecase.EmployeeProfileUseCase;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.company.application.views.mainlayout.MainLayout;
import com.vaadin.flow.router.RouteAlias;

import java.util.Optional;

@PageTitle("Profil")
@Route(value = "profile", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class ProfileView extends Main {
    private final EmployeeProfileUseCase employeeProfileUseCase;
    private final GermanDateService dateService;
    private EmployeeProfile employee;

    private final VerticalLayout pageContent = new VerticalLayout();

    public ProfileView(EmployeeProfileUseCase employeeProfileUseCase, GermanDateService dateService) {
        this.employeeProfileUseCase = employeeProfileUseCase;
        this.dateService = dateService;

        addClassNames("justify-center");
        Div mainDiv = new Div();
        mainDiv.addClassNames("profile-main-div", "pt-m");
        pageContent.setPadding(true);
        pageContent.setSpacing(false);

        Optional<EmployeeProfile> employeeOptional = employeeProfileUseCase.getCurrentUser();
        if (employeeOptional.isPresent()) {
            employee = employeeOptional.get();
            pageContent.add(getProfileHeader(), getMainContent());
        } else {
            pageContent.add(new Paragraph("Employee does not exits"));
        }

        mainDiv.add(pageContent);
        add(mainDiv);
    }

    public Component getProfileHeader() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addClassNames("items-center", "h-auto", "flex", "pb-m");
        layout.add(getProfilePhoto(), getNameAndEmail());
        return layout;
    }

    private Image getProfilePhoto() {
        Image profilePhoto = new Image();
        profilePhoto.setSrc(employee.getProfilePicture());
        profilePhoto.setAlt("ProfilePhoto");
        profilePhoto.addClassNames("profile-photo");

        return profilePhoto;
    }

    private Component getNameAndEmail() {
        VerticalLayout layout = new VerticalLayout();

        layout.addClassNames("pl-m", "content-between");
        H1 name = new H1(employee.getDisplayName());
        name.addClassNames("mb-0", "text-l", "pt-0");
        Paragraph email = new Paragraph(employee.getEmail());
        email.addClassNames("mt-0", "text-m", "text-secondary");

        layout.add(name, email);
        return layout;
    }

    private Component getMainContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(false);
        layout.setSpacing(false);
        layout.add(
                new InfoRow("Geburtsdatum", dateService.getGermanDate(employee.getDateOfBirth())),
                new InfoRow("Abteilung", employee.getOccupation().getUiText()),
                new InfoRow("Durchwahl", employee.getPhone()),
                new InfoRow("Adresse", employee.getAddressUiText()),
                new InfoRow("Rolle", employee.getRole().getUiText()));
        return layout;
    }
}

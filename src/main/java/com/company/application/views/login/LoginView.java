package com.company.application.views.login;

import com.company.application.domain.login.data.LoginData;
import com.company.application.domain.login.usecase.LoginUseCase;
import com.company.application.views.employee.EmployeeView;
import com.company.application.views.login.components.LoginInformationCard;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.Validator;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.Route;
import org.springframework.security.authentication.BadCredentialsException;

@Route(value = "login")
public class LoginView extends Main {

    private final LoginUseCase loginUseCase;
    private final EmailField email = new EmailField();
    private final PasswordField password = new PasswordField();

    public LoginView(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
        addClassName("login-view");

        Div mainDiv = new Div();
        mainDiv.addClassNames("main-div");
        VerticalLayout pageContent = new VerticalLayout();
        pageContent.setPadding(true);
        pageContent.setSpacing(false);

        initDataBinder();
        pageContent.add(getLogo(), getLoginHeader(), getLoginPage());

        mainDiv.add(pageContent);
        add(mainDiv);
    }

    private void initDataBinder() {
        Binder<LoginData> binder = new Binder<>(LoginData.class);
        binder.forField(email)
                .withValidator(new EmailValidator("Bitte eine valide E-Mail-Adresse eingeben"))
                .bind(LoginData::getEmail, LoginData::setEmail);

        binder.forField(password)
                .withValidator((Validator<String>) (string, valueContext) -> {
                    if (string.isEmpty()) {
                        return ValidationResult.error("Bitte ein Passwort eingeben");
                    }
                    if (string.length() < 4) {
                        return ValidationResult.error("Das Passwort ist zu kurz");
                    }

                    return ValidationResult.ok();
                }).bind(LoginData::getPassword, LoginData::setPassword);

        binder.setBean(new LoginData());
    }

    public Component getLogo() {
        Div layout = new Div();
        layout.addClassNames("center-layout");
        Image logo = new Image("images/company_logo.png", "RICH solutions");
        logo.addClassName("company-logo");
        layout.add(logo);
        return layout;
    }

    public Component getLoginHeader() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.addClassName("border-layout");
        Span header = new Span("Bitte melden Sie sich an");
        header.addClassName("login-header");

        layout.add(header);
        return layout;
    }

    public Component getLoginPage() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setWidthFull();
        layout.add(getLoginForm(), new LoginInformationCard());
        return layout;
    }

    public Component getLoginForm() {
        VerticalLayout layout = new VerticalLayout();
        layout.addClassName("login-form");

        email.setWidthFull();
        email.setErrorMessage("Bitte eine valide E-Mail-Adresse eingeben");
        email.setLabel("E-Mail-Adresse");

        password.setWidthFull();
        password.setReadOnly(false);
        password.setEnabled(true);
        password.setLabel("Passwort");

        Button loginButton = new Button();
        loginButton.setText("Login");
        loginButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        loginButton.addClickListener(event -> onLoginSubmit());
        loginButton.addClickShortcut(Key.ENTER);

        layout.add(email, password, loginButton);

        return layout;
    }

    private void onLoginSubmit() {
        if (email.getValue().isEmpty()) {
            email.setInvalid(true);
            email.setErrorMessage("Bitte eine E-Mail-Adresse eingeben");
        } else {
            try {
                if (loginUseCase.login(new LoginData(email.getValue().toLowerCase(), password.getValue()))) {
                    UI.getCurrent().navigate(EmployeeView.class);
                } else {
                    email.setInvalid(true);
                    email.setErrorMessage(null);
                    password.setInvalid(true);
                    password.setErrorMessage("Ein unerwarteter Fehler ist aufgetreten.");
                }
            } catch (BadCredentialsException e) {
                email.setInvalid(true);
                email.setErrorMessage(null);
                password.setInvalid(true);
                password.setErrorMessage("Die E-Mail-Adresse oder das Passwort sind nicht korrekt.");
            }
        }
    }
}

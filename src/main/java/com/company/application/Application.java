package com.company.application;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import gui.customcomponents.startframe.ApplicationStartFrame;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.vaadin.artur.helpers.LaunchUtil;

import javax.annotation.PostConstruct;

/**
 * The entry point of the Spring Boot application.
 *
 * @author Thorsten Zieres, 1297197
 */
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@Theme(value = "companyportal")
@PWA(name = "Company Portal", shortName = "Company Portal")
@Push
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
@NpmPackage(value = "line-awesome", version = "1.3.0")
@CssImport(value = "./themes/companyportal/components/vaadin-text-area.css", themeFor = "vaadin-text-area")
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

    public static void main(String[] args) {
        LaunchUtil.launchBrowserInDevelopmentMode(SpringApplication.run(Application.class, args));
    }

    @PostConstruct
    public void createStartFrame() {
        System.setProperty("java.awt.headless", "false");
        ApplicationStartFrame frame = new ApplicationStartFrame();
        frame.setVisible(true);
    }
}

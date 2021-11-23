package com.company.application.views.core.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * @author Thorsten Zieres, 1297197
 */
public class PageHeader extends HorizontalLayout {

    public PageHeader(String header, String subtitle) {
        addClassNames("page-header");
        add(getHeader(header, subtitle));
    }

    public PageHeader(String picture, String header, String subtitle) {
        addClassNames("page-header");
        add(getProfilePhoto(picture), getHeader(header, subtitle));
    }

    private Image getProfilePhoto(String picture) {
        Image profilePhoto = new Image();
        profilePhoto.setSrc(picture);
        profilePhoto.setAlt("ProfilePhoto");
        profilePhoto.setId("profile-photo");

        return profilePhoto;
    }

    private Component getHeader(String header, String subtitle) {
        VerticalLayout layout = new VerticalLayout();

        layout.addClassNames("header");
        H1 name = new H1(header);
        name.addClassNames("header-text");
        Paragraph email = new Paragraph(subtitle);
        email.addClassNames("subtitle");

        layout.add(name, email);
        return layout;
    }
}

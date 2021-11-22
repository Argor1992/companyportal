package com.company.application.views.core.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class PageHeader extends HorizontalLayout {

    public PageHeader(String header, String subtitle) {
        addClassNames("items-center", "h-auto", "flex", "pb-m");
        add(getHeader(header, subtitle));
    }

    public PageHeader(String picture, String header, String subtitle) {
        addClassNames("items-center", "h-auto", "flex", "pb-m");
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

        layout.addClassNames("pl-s", "content-between");
        H1 name = new H1(header);
        name.addClassNames("mb-0", "text-xl", "pt-0");
        Paragraph email = new Paragraph(subtitle);
        email.addClassNames("mt-0", "text-l", "text-secondary");

        layout.add(name, email);
        return layout;
    }
}

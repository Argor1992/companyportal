package com.company.application.views.about;

import com.company.application.views.core.mainlayout.MainLayout;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

/**
 * @author Thorsten Zieres, 1297197
 */
@PageTitle("Über uns")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends VerticalLayout {

    public AboutView() {
        add(new Paragraph("Über uns"));
    }

}

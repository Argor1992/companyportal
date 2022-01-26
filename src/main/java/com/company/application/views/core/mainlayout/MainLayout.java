package com.company.application.views.core.mainlayout;

import com.company.application.views.clientlist.ClientListView;
import com.company.application.views.employee.EmployeeView;
import com.company.application.views.employeelist.EmployeeListView;
import com.company.application.views.projectdocuments.ProjectDocumentsView;
import com.company.application.views.projectlist.ProjectListView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * The main view is a top-level placeholder for other views.
 *
 * @author Thorsten Zieres, 1297197
 */
@PageTitle("Main")
public class MainLayout extends AppLayout {
    private H1 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent());
    }

    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassNames("text-secondary", "clickable");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H1();
        viewTitle.addClassNames("m-0", "text-l");

        Div titleLogout = new Div();
        titleLogout.addClassNames("flex", "items-center", "justify-between", "w-full", "h-full");
        titleLogout.add(viewTitle, createLogoutButton());


        Header header = new Header(toggle, titleLogout);
        header.addClassNames("bg-base", "border-b", "border-contrast-10", "box-border", "flex", "h-xl", "items-center",
                "w-full");
        return header;
    }

    private Component createLogoutButton() {
        Button logout = new Button(new Icon(VaadinIcon.SIGN_OUT));
        logout.addClassNames("text-secondary", "text-xl", "clickable");
        logout.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        logout.addClickListener(buttonClickEvent -> {
            SecurityContextHolder.clearContext();
            UI.getCurrent().getSession().close();
            UI.getCurrent().navigate("/login");
        });
        return logout;
    }

    private Component createDrawerContent() {
        H1 appName = new H1("RICH solutions");
        appName.addClassNames("flex", "items-center", "h-xl", "m-0", "px-m", "text-m");

        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(appLogo(),
                createNavigation(), createFooter());
        section.addClassNames("flex", "flex-col", "items-stretch", "max-h-full", "min-h-full");
        return section;
    }

    private Component appLogo() {
        Div layout = new Div();
        layout.addClassNames("text-center");
        Image logo = new Image("images/company_logo.png", "RICH solutions");
        logo.addClassName("company-logo");
        layout.add(logo);
        return layout;
    }

    private Nav createNavigation() {
        Nav nav = new Nav();
        nav.addClassNames("border-b", "border-contrast-10", "flex-grow", "overflow-auto");
        nav.getElement().setAttribute("aria-labelledby", "views");

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames("list-none", "m-0", "p-0");
        nav.add(list);

        for (RouterLink link : createLinks()) {
            ListItem item = new ListItem(link);
            list.add(item);
        }
        return nav;
    }

    private List<RouterLink> createLinks() {
        MenuItemInfo[] menuItems = new MenuItemInfo[]{ //
                new MenuItemInfo("Profil", "la la-user-circle", EmployeeView.class), //

                new MenuItemInfo("Mitarbeiterübersicht", "la la-th-list", EmployeeListView.class), //

                new MenuItemInfo("Kundenübersicht", "la la-money-bill-wave", ClientListView.class), //

                new MenuItemInfo("Projektübersicht", "la la-tasks", ProjectListView.class), //

                new MenuItemInfo("Projektunterlagen", "la la-edit", ProjectDocumentsView.class), //

        };
        List<RouterLink> links = new ArrayList<>();
        for (MenuItemInfo menuItemInfo : menuItems) {
            links.add(createLink(menuItemInfo));

        }
        return links;
    }

    private static RouterLink createLink(MenuItemInfo menuItemInfo) {
        RouterLink link = new RouterLink();
        link.addClassNames("flex", "mx-s", "p-s", "relative", "text-secondary");
        link.setRoute(menuItemInfo.getView());

        Span icon = new Span();
        icon.addClassNames("me-s", "text-l");
        if (!menuItemInfo.getIconClass().isEmpty()) {
            icon.addClassNames(menuItemInfo.getIconClass());
        }

        Span text = new Span(menuItemInfo.getText());
        text.addClassNames("font-medium", "text-s");

        link.add(icon, text);
        return link;
    }

    private Footer createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("flex", "items-center", "my-s", "px-m", "py-xs");

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}

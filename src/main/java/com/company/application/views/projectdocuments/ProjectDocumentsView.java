package com.company.application.views.projectdocuments;

import com.company.application.domain.projectdocuments.data.ServerFile;
import com.company.application.domain.projectdocuments.usecase.CreateFileTreeUseCase;
import com.company.application.views.projectdocuments.component.FileTree;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.DescriptionList.Description;
import com.vaadin.flow.component.html.DescriptionList.Term;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.company.application.views.core.mainlayout.MainLayout;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author Thorsten Zieres, 1297197
 */
@PageTitle("Unterlagen")
@Route(value = "editor", layout = MainLayout.class)
public class ProjectDocumentsView extends Div {

    private final CreateFileTreeUseCase fileTreeUseCase;

    public ProjectDocumentsView(CreateFileTreeUseCase fileTreeUseCase) {
        this.fileTreeUseCase = fileTreeUseCase;
        addClassNames("flex", "flex-grow", "h-full");
        setHeight("100%");
        add(new FileSelector(fileTreeUseCase));
//        add(new EmbeddedPdfDocument(new StreamResource("introduction-tasks.pdf", () -> {
//            try {
//                return getPdfInputStream();
//            } catch (FileNotFoundException e) {
//                return new ByteArrayInputStream(new byte[]{});
//            }
//        })));

        TextArea text = new TextArea();
        text.setWidth("100%");
        text.addClassNames("my-text-area");
        StringBuilder value = new StringBuilder(1_000);
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream("./projectfiles/ThreePowersv2.java");
            sc = new Scanner(inputStream, StandardCharsets.UTF_8);

            while (sc.hasNext()) {
                value.append(sc.nextLine()).append("\n");
            }

        } catch (IOException e) {
            value.append("Exception");
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ignored) {}
            }

            if (sc != null) {
                sc.close();
            }
        }

        text.setValue(value.toString());
        text.setReadOnly(false);

        add(text);


//        add(new EmbeddedPdfDocument("images/introduction-tasks.pdf"));


    }

    private InputStream getPdfInputStream() throws FileNotFoundException {
        return new FileInputStream("./projectfiles/introduction-tasks.pdf");
    }

    private Section createSidebar() {
        Section sidebar = new Section();
        sidebar.addClassNames("bg-contrast-20", "box-border", "flex", "flex-col", "flex-shrink-0", "overflow-auto", "h-full");
        sidebar.setWidth("256px");

//        H2 title = new H2("Project details");
//        title.addClassName("sr-only"); // Only shown for screen readers
//
//        DescriptionList dl = new DescriptionList();
//        dl.addClassNames("flex", "flex-col", "gap-l", "mb-s", "mt-0", "text-s");
//
//        dl.add(createItem("Owner", "My Name"), createItem("Created", "2021-08-14 14:48"),
//                createItem("Last modified", "2021-08-14 14:50"), createBadgeItem("Status", "Draft"));
//
//        Select select = new Select("Project");
//        select.setLabel("Project");
//        select.setItems("My Project", "Your Project", "Their Project");
//        select.setValue("My Project");

        // Add it all together
//        sidebar.add(title, dl, select);




        sidebar.add(new FileSelector(fileTreeUseCase));

        return sidebar;
    }

    private Div createItem(String label, String value) {
        return new Div(createTerm(label), createDescription(value));
    }

    private Div createBadgeItem(String label, String value) {
        return new Div(createTerm(label), createDescription(value, "badge"));
    }

    private Term createTerm(String label) {
        Term term = new Term(label);
        term.addClassNames("font-medium", "text-secondary");
        return term;
    }

    private Description createDescription(String value, String... themeNames) {
        Description desc = new Description(value);
        desc.addClassName("ml-0");
        for (String themeName : themeNames) {
            desc.getElement().getThemeList().add(themeName);
        }
        return desc;
    }
}

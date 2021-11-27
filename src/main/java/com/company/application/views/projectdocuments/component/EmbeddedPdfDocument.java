package com.company.application.views.projectdocuments.component;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.server.StreamResource;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Tag("object")
public class EmbeddedPdfDocument extends Component implements HasSize {
    protected EmbeddedPdfDocument() {
        getElement().setAttribute("type", "application/pdf");
        setSizeFull();
    }

    public EmbeddedPdfDocument(String path) {
        this();
        getElement().setAttribute("data", new StreamResource("introduction-tasks.pdf", () -> {
            try {
                return getPdfInputStream(path);
            } catch (FileNotFoundException e) {
                return new ByteArrayInputStream(new byte[]{});
            }
        }));
    }

    private InputStream getPdfInputStream(String path) throws FileNotFoundException {
        return new FileInputStream(path);
    }
}

package com.company.application.views.projectdocuments.component.fileeditor.text;

import com.vaadin.flow.component.textfield.TextArea;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TextEditor extends TextArea {
    public TextEditor(String path, boolean isEnabled) {
        setWidth("100%");
        addClassNames("my-text-area");
        StringBuilder value = new StringBuilder(1_000);
        try (FileInputStream inputStream = new FileInputStream(path); Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8)) {
            while (sc.hasNext()) {
                value.append(sc.nextLine()).append("\n");
            }

        } catch (IOException e) {
            value.append("Exception");
            e.printStackTrace();
        }

        setValue(value.toString());
        setReadOnly(!isEnabled);
    }

    public void setEnabled() {
        setReadOnly(false);
    }

    public void setDisabled() {
        setReadOnly(true);
    }
}

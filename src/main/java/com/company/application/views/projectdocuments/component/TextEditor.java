package com.company.application.views.projectdocuments.component;

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
        FileInputStream inputStream = null;
        Scanner sc = null;
        try {
            inputStream = new FileInputStream(path);
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

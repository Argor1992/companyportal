package com.company.application.views.client;

import com.company.application.core.domain.IClient;
import com.company.application.domain.core.data.Address;
import com.company.application.views.core.components.InfoRow;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ContactCard extends VerticalLayout {
    public ContactCard(IClient client, Address address) {
        addClassNames("info-card", "card", "bg-contrast-10", "rounded-l", "p-m");
        setSpacing(false);
        setPadding(false);

        H1 header = new H1("Ansprechpartner:");
        header.addClassNames("m-0", "text-xl", "pt-0", "pb-s");

        VerticalLayout info = new VerticalLayout();
        info.setMargin(false);
        info.setSpacing(false);
        info.setPadding(false);
        info.add(
                new InfoRow("AnsprechpartnerIn", client.getRepresentative()),
                new InfoRow("E-Mail", client.getEmail()),
                new InfoRow("Telefon", client.getPhone()),
                new InfoRow("Adresse", address.getUiText())
        );

        add(header, info);
    }
}

package com.company.application.views.client;

import com.company.application.core.domain.IClient;
import com.company.application.domain.core.data.Address;
import com.company.application.views.core.components.InfoRow;
import com.company.application.views.core.components.card.Card;

import java.util.List;

public class ContactCard extends Card {
    public ContactCard(IClient client, Address address) {
        super("AnsprechpartnerIn", List.of(
                new InfoRow("Name", client.getRepresentative()),
                new InfoRow("E-Mail", client.getEmail()),
                new InfoRow("Telefon", client.getPhone()),
                new InfoRow("Adresse", address.getUiText())
        ));
    }
}

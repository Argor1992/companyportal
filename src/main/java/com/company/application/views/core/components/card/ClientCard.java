package com.company.application.views.core.components.card;

import com.company.application.core.domain.IClient;

import java.util.List;
import java.util.stream.Collectors;

public class ClientCard extends Card {

    public ClientCard(List<IClient> clients) {
        super(
                "Kunden",
                clients.stream().map(client -> new CardRow(
                        "client/" + client.getId(),
                        client.getName(),
                        "VertreterIn: " + client.getRepresentative(),
                        client.getEmail()
                )).collect(Collectors.toList())
        );
    }
}

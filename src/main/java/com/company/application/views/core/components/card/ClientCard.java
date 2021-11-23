package com.company.application.views.core.components.card;

import com.company.application.domain.core.data.IClient;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Thorsten Zieres, 1297197
 */
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

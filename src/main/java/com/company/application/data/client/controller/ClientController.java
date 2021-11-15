package com.company.application.data.client.controller;

import com.company.application.data.client.service.ClientService;
import com.company.application.domain.clientlist.data.ClientOverview;
import com.company.application.domain.clientprofile.data.Client;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    public Optional<ClientOverview> getClientOverview(int id) {
        if (id < 1)
            return Optional.empty();
        return clientService.getClientOverview(id);
    }

    public Optional<Client> getClient(Integer id) {
        if (id < 1)
            return Optional.empty();
        return clientService.getFullClient(id);
    }

    public List<ClientOverview> getClientOverviewList() {
        return clientService.getClientOverviewList();
    }

    public boolean updateClient(ClientOverview client) {
        return clientService.updateClient(client);
    }
}

package com.company.application.data.client.controller;

import com.company.application.data.client.service.ClientService;
import org.springframework.stereotype.Controller;

@Controller
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
}

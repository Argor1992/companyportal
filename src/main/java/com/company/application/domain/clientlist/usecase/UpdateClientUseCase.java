package com.company.application.domain.clientlist.usecase;

import com.company.application.data.client.controller.ClientController;
import com.company.application.domain.clientlist.data.ClientOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateClientUseCase {
    @Autowired
    private ClientController clientController;

    public boolean updateClient(ClientOverview client) {
        return clientController.updateClient(client);
    }
}

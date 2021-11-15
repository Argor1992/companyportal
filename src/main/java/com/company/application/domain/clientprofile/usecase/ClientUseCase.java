package com.company.application.domain.clientprofile.usecase;

import com.company.application.data.client.controller.ClientController;
import com.company.application.domain.clientprofile.data.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientUseCase {
    @Autowired
    private ClientController clientController;

    public Optional<Client> getClient(Integer id) {
        return clientController.getClient(id);
    }
}

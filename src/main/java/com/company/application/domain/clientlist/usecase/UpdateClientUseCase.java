package com.company.application.domain.clientlist.usecase;

import com.company.application.data.client.controller.ClientController;
import com.company.application.domain.clientlist.data.ClientOverview;
import com.company.application.domain.core.usecase.IUpdateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Thorsten Zieres, 1297197
 */
@Service
public class UpdateClientUseCase implements IUpdateUseCase<ClientOverview> {
    @Autowired
    private ClientController clientController;

    @Override
    public boolean update(ClientOverview selected) {
        return clientController.updateClient(selected);
    }
}

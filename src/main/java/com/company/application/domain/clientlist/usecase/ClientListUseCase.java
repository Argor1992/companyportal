package com.company.application.domain.clientlist.usecase;

import com.company.application.core.security.SecurityController;
import com.company.application.data.client.controller.ClientController;
import com.company.application.data.employee.entity.Occupation;
import com.company.application.data.employee.entity.Role;
import com.company.application.domain.clientlist.data.ClientOverview;
import com.company.application.domain.core.usecase.IListUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientListUseCase implements IListUseCase<ClientOverview> {
    @Autowired
    private ClientController clientController;
    @Autowired
    private SecurityController securityController;

    @Override
    public List<ClientOverview> getList() {
        return clientController.getClientOverviewList();
    }

    @Override
    public Optional<ClientOverview> getObject(int id) {
        return clientController.getClientOverview(id);
    }

    public boolean showUpdateMenu() {
        Optional<Role> role = securityController.getCurrentUserRole();
        Optional<Occupation> occupation = securityController.getCurrentUserOccupation();
        return role.isPresent() && occupation.isPresent() && (role.get() == Role.ADMIN || occupation.get() == Occupation.MANAGEMENT);
    }
}

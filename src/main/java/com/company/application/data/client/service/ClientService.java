package com.company.application.data.client.service;

import com.company.application.core.domain.IEmployee;
import com.company.application.core.domain.IProject;
import com.company.application.data.client.entity.ClientEntity;
import com.company.application.data.client.repository.ClientRepository;
import com.company.application.data.project.entity.ProjectState;
import com.company.application.domain.clientlist.data.ClientOverview;
import com.company.application.domain.clientprofile.data.Client;
import com.company.application.domain.clientprofile.data.EmployeeRelationship;
import com.company.application.domain.core.data.Address;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Optional<ClientOverview> getClientOverview(int id) {
        return clientRepository.findClientOverviewById(id).map(this::dtoToClientOverview);
    }

    public List<ClientOverview> getClientOverviewList() {
        return clientRepository.findAllClientOverviewsBy().stream().map(this::dtoToClientOverview).collect(Collectors.toList());
    }

    @Transactional
    public boolean updateClient(ClientOverview client) {
        return clientRepository.updateClientOverview(
                client.getId(),
                client.getName(),
                client.getRepresentative(),
                client.getEmail(),
                client.getPhone()
        ) == 1;
    }

    public Optional<Client> getFullClient(Integer id) {
        return clientRepository.findClientByIdAndFetchEagerly(id).map(this::entityToClient);
    }

    private ClientOverview dtoToClientOverview(ClientOverview clientOverview) {
        return new ClientOverview(
                clientOverview.getId(),
                clientOverview.getName(),
                clientOverview.getRepresentative(),
                clientOverview.getEmail(),
                clientOverview.getPhone()
        );
    }

    private Client entityToClient(ClientEntity clientEntity) {
        return new Client(
                clientEntity.getId(),
                clientEntity.getName(),
                clientEntity.getRepresentative(),
                clientEntity.getEmail(),
                clientEntity.getPhone(),
                new Address(
                        clientEntity.getAddress().getStreet(),
                        clientEntity.getAddress().getPostalCode(),
                        clientEntity.getAddress().getCity(),
                        clientEntity.getAddress().getState(),
                        clientEntity.getAddress().getCountry()
                ),
                clientEntity.getContactPersons().stream().map(relation -> new EmployeeRelationship(
                        new IEmployee() {
                            @Override
                            public Integer getId() { return relation.getEmployee().getId(); }
                            @Override
                            public String getPersonnelNumber() { return relation.getEmployee().getPersonnelNumber(); }
                            @Override
                            public String getFirstName() { return relation.getEmployee().getFirstName(); }
                            @Override
                            public String getLastName() { return relation.getEmployee().getLastName(); }
                            @Override
                            public String getEmail() { return relation.getEmployee().getEmail(); }
                            @Override
                            public String getPhone() { return relation.getEmployee().getPhone(); }
                            @Override
                            public LocalDate getDateOfBirth() { return relation.getEmployee().getDateOfBirth(); }
                            @Override
                            public String getProfilePicture() { return relation.getEmployee().getProfilePicture(); }
                        },
                        relation.getDate()
                )).collect(Collectors.toList()),
                clientEntity.getProjects().stream().map(projectEntity -> new IProject() {
                    @Override
                    public Integer getId() { return projectEntity.getId(); }
                    @Override
                    public String getName() { return projectEntity.getName(); }
                    @Override
                    public String getDescription() { return projectEntity.getDescription(); }
                    @Override
                    public double getAmount() { return projectEntity.getAmount(); }
                    @Override
                    public LocalDate getDate() { return projectEntity.getDate(); }
                    @Override
                    public ProjectState getProjectState() { return projectEntity.getProjectState(); }
                    @Override
                    public int getPriority() { return projectEntity.getPriority(); }
                }).collect(Collectors.toList())
        );
    }
}

package com.company.application.data.relations.entity;

import com.company.application.data.client.entity.ClientEntity;
import com.company.application.data.employee.entity.EmployeeEntity;

import javax.persistence.*;

@Entity
@Table(name = "EmployeeClientRelation")
public class EmployeeClientRelationEntity {
    @EmbeddedId
    private EmployeeClientKey id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("employeeId")
    @JoinColumn(name = "employeeId")
    private EmployeeEntity employee;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("clientId")
    @JoinColumn(name = "clientId")
    private ClientEntity client;

    public EmployeeClientRelationEntity() {
    }

    public EmployeeClientRelationEntity(EmployeeEntity employee, ClientEntity client) {
        this.id = new EmployeeClientKey(employee.getId(), client.getId());
        this.employee = employee;
        this.client = client;
    }

    public EmployeeClientKey getId() { return id; }
    public EmployeeEntity getEmployee() { return employee; }
    public ClientEntity getClient() { return client; }
}

package com.company.application.data.relations.entity;

import com.company.application.data.client.entity.ClientEntity;
import com.company.application.data.employee.entity.EmployeeEntity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Thorsten Zieres, 1297197
 */
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

    private LocalDate date;

    public EmployeeClientRelationEntity() {
    }

    public EmployeeClientRelationEntity(EmployeeEntity employee, ClientEntity client, LocalDate date) {
        this.date = date;
        this.id = new EmployeeClientKey(employee.getId(), client.getId());
        this.employee = employee;
        this.client = client;
    }

    public EmployeeClientKey getId() { return id; }
    public EmployeeEntity getEmployee() { return employee; }
    public ClientEntity getClient() { return client; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}

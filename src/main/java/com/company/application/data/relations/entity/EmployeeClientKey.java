package com.company.application.data.relations.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeClientKey {
    @Column(name = "employeeId")
    private long employeeId;

    @Column(name = "clientId")
    private long clientId;

    public EmployeeClientKey(){}

    public EmployeeClientKey(long employeeId, long clientId) {
        this.employeeId = employeeId;
        this.clientId = clientId;
    }

    public long getEmployeeId() { return employeeId; }
    public long getClientId() { return clientId; }
}

package com.company.application.data.relations.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EmployeeClientKey implements Serializable {
    @Column(name = "employeeId")
    private Integer employeeId;

    @Column(name = "clientId")
    private Integer clientId;

    public EmployeeClientKey(){}

    public EmployeeClientKey(int employeeId, int clientId) {
        this.employeeId = employeeId;
        this.clientId = clientId;
    }

    public Integer getEmployeeId() { return employeeId; }
    public Integer getClientId() { return clientId; }
}

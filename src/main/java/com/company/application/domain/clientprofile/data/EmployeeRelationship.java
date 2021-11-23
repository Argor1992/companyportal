package com.company.application.domain.clientprofile.data;

import com.company.application.domain.core.data.IEmployee;

import java.time.LocalDate;

/**
 * @author Thorsten Zieres, 1297197
 */
public class EmployeeRelationship {
    private IEmployee contact;
    private LocalDate since;

    public EmployeeRelationship(IEmployee contact, LocalDate since) {
        this.contact = contact;
        this.since = since;
    }

    public IEmployee getContact() { return contact; }
    public void setContact(IEmployee contact) { this.contact = contact; }
    public LocalDate getSince() { return since; }
    public void setSince(LocalDate since) { this.since = since; }
}

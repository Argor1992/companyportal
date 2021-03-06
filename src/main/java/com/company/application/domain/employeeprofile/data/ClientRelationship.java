package com.company.application.domain.employeeprofile.data;

import com.company.application.domain.core.data.IClient;

import java.time.LocalDate;

/**
 * @author Thorsten Zieres, 1297197
 */
public class ClientRelationship {
    private IClient contact;
    private LocalDate since;

    public ClientRelationship(IClient contact, LocalDate since) {
        this.contact = contact;
        this.since = since;
    }

    public IClient getContact() { return contact; }
    public void setContact(IClient contact) { this.contact = contact; }
    public LocalDate getSince() { return since; }
    public void setSince(LocalDate since) { this.since = since; }
}

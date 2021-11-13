package com.company.application.data.client.entity;

import com.company.application.core.data.AbstractEntity;
import com.company.application.data.address.entity.AddressEntity;
import com.company.application.data.project.entity.ProjectEntity;
import com.company.application.data.relations.entity.EmployeeClientRelationEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Client")
public class ClientEntity extends AbstractEntity {
    private String name;

    @Column(name="email", nullable = false, unique = true)
    private String email;
    private String phone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private AddressEntity address;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<EmployeeClientRelationEntity> contactPersons = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "projectClients")
    private Set<ProjectEntity> projects = new HashSet<>();

    public ClientEntity() {
    }

    public ClientEntity(String name, String email, String phone, AddressEntity address, Set<EmployeeClientRelationEntity> contactPersons, Set<ProjectEntity> projects) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.contactPersons = contactPersons;
        this.projects = projects;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public AddressEntity getAddress() { return address; }
    public void setAddress(AddressEntity address) { this.address = address; }
    public Set<EmployeeClientRelationEntity> getContactPersons() { return contactPersons; }
    public void setContactPersons(Set<EmployeeClientRelationEntity> contactPersons) { this.contactPersons = contactPersons; }
    public Set<ProjectEntity> getProjects() { return projects; }
    public void setProjects(Set<ProjectEntity> projects) { this.projects = projects; }
}
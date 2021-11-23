package com.company.application.domain.clientprofile.data;

import com.company.application.core.domain.IClient;
import com.company.application.core.domain.IProject;
import com.company.application.domain.core.data.Address;

import java.util.List;

public class Client implements IClient {
    private int id;
    private String name;
    private String representative;
    private String email;
    private String phone;
    private Address address;
    private List<EmployeeRelationship> contactPersons;
    private List<IProject> projects;

    public Client(int id, String name, String representative, String email, String phone, Address address, List<EmployeeRelationship> contactPersons, List<IProject> projects) {
        this.id = id;
        this.name = name;
        this.representative = representative;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.contactPersons = contactPersons;
        this.projects = projects;
    }

    @Override
    public Integer getId() { return id; }
    @Override
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    @Override
    public String getRepresentative() { return representative; }
    public void setRepresentative(String representative) { this.representative = representative; }
    @Override
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    @Override
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    public List<EmployeeRelationship> getContactPersons() { return contactPersons; }
    public void setContactPersons(List<EmployeeRelationship> contactPersons) { this.contactPersons = contactPersons; }
    public List<IProject> getProjects() { return projects; }
    public void setProjects(List<IProject> projects) { this.projects = projects; }
}
package com.company.application.domain.employeeprofile.data;

import com.company.application.core.domain.IEmployee;
import com.company.application.core.domain.IProject;
import com.company.application.data.employee.entity.Occupation;
import com.company.application.data.employee.entity.Role;
import com.company.application.domain.core.data.Address;

import java.time.LocalDate;
import java.util.List;

public class Employee implements IEmployee {
    private final int id;
    private final String personnelNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private Occupation occupation;
    private Address address;
    private Role role;
    private String profilePicture;
    private List<ClientRelationship> clients;
    private List<IProject> projects;

    public Employee(int id, String personnelNumber, String firstName, String lastName, String email,
                    String phone, LocalDate dateOfBirth, Occupation occupation, Address address,
                    Role role, String profilePicture, List<ClientRelationship> clients, List<IProject> projects) {
        this.id = id;
        this.personnelNumber = personnelNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
        this.address = address;
        this.role = role;
        this.profilePicture = profilePicture;
        this.clients = clients;
        this.projects = projects;
    }

    @Override
    public int getId() { return id; }
    @Override
    public String getPersonnelNumber() { return personnelNumber; }
    @Override
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    @Override
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    @Override
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    @Override
    public String getPhone() { return phone; }
    public void setPhone(String phone) {this.phone = phone;}
    @Override
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public Occupation getOccupation() { return occupation; }
    public void setOccupation(Occupation occupation) { this.occupation = occupation; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }
    public List<ClientRelationship> getClients() { return clients; }
    public void setClients(List<ClientRelationship> clients) { this.clients = clients; }
    public List<IProject> getProjects() { return projects; }

    public void setProjects(List<IProject> projects) {
        this.projects = projects;
    }

    public String getDisplayName() {
        return firstName + " " + lastName + " (" + personnelNumber + ")";
    }
}

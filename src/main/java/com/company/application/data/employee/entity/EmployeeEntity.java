package com.company.application.data.employee.entity;

import com.company.application.core.data.AbstractEntity;
import com.company.application.data.address.entity.AddressEntity;
import com.company.application.data.project.entity.ProjectEntity;
import com.company.application.data.relations.entity.EmployeeClientRelationEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Employee")
public class EmployeeEntity extends AbstractEntity {

    @Column(name="personnelNumber", nullable = false, unique = true)
    private String personnelNumber;
    private String firstName;
    private String lastName;

    @Column(name="email", nullable = false, unique = true)
    private String email;
    private String phone;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private AddressEntity address;
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Occupation occupation;

    @Column(name="role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private String profilePicture;

    @Column(name="password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "employee")
    private Set<EmployeeClientRelationEntity> clients = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "projectManagers")
    private Set<ProjectEntity> projects = new HashSet<>();

    public EmployeeEntity() {
    }

    public EmployeeEntity(String personnelNumber, String firstName, String lastName, String email, String phone, AddressEntity address, LocalDate dateOfBirth, Occupation occupation, Role role, String profilePicture, String password, Set<EmployeeClientRelationEntity> clients, Set<ProjectEntity> projects) {
        this.personnelNumber = personnelNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
        this.role = role;
        this.profilePicture = profilePicture;
        this.password = password;
        this.clients = clients;
        this.projects = projects;
    }

    public String getPersonnelNumber() { return personnelNumber; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public AddressEntity getAddress() { return address; }
    public void setAddress(AddressEntity addressEntity) { this.address = addressEntity; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public Occupation getOccupation() { return occupation; }
    public void setOccupation(Occupation occupation) { this.occupation = occupation; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public List<EmployeeClientRelationEntity> getClients() { return new ArrayList<>(clients); }
    public void setClients(Set<EmployeeClientRelationEntity> clients) { this.clients = clients; }
    public Set<ProjectEntity> getProjects() { return projects; }
    public void setProjects(Set<ProjectEntity> projects) { this.projects = projects; }
}

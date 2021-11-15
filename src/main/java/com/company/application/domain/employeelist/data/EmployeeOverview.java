package com.company.application.domain.employeelist.data;

import com.company.application.core.domain.IEmployee;
import com.company.application.data.employee.entity.Occupation;

import java.time.LocalDate;

public class EmployeeOverview implements IEmployee {
    private final int id;
    private final String personnelNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private Occupation occupation;
    private String profilePicture;

    public EmployeeOverview(int id, String personnelNumber, String firstName, String lastName, String email, String phone, LocalDate dateOfBirth, Occupation occupation, String profilePicture) {
        this.id = id;
        this.personnelNumber = personnelNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
        this.profilePicture = profilePicture;
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
    @Override
    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }
}

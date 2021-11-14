package com.company.application.domain.employeeprofile.data;

import com.company.application.core.domain.IEmployee;
import com.company.application.data.employee.entity.Occupation;

import java.time.LocalDate;

public class EmployeeProfile implements IEmployee {
    private final int id;
    private final String personnelNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LocalDate dateOfBirth;
    private Occupation occupation;

    public EmployeeProfile() {
        id = 0;
        personnelNumber = "";
    }

    public EmployeeProfile(int id, String personnelNumber, String firstName, String lastName, String email, String phone, LocalDate dateOfBirth, Occupation occupation) {
        this.id = id;
        this.personnelNumber = personnelNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.occupation = occupation;
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
}

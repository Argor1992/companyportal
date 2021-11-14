package com.company.application.data.employee.dtos;

import com.company.application.data.employee.entity.Occupation;

import java.time.LocalDate;

public interface EmployeeOverviewDto {
    int getId();
    String getPersonnelNumber();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPhone();
    LocalDate getDateOfBirth();
    Occupation getOccupation();
}

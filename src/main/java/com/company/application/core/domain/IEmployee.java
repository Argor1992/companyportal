package com.company.application.core.domain;

import com.company.application.data.employee.entity.Occupation;

import java.time.LocalDate;

public interface IEmployee {
    int getId();
    String getPersonnelNumber();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPhone();
    LocalDate getDateOfBirth();
    Occupation getOccupation();
}

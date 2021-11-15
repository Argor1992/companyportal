package com.company.application.core.domain;

import java.time.LocalDate;

public interface IEmployee {
    int getId();
    String getPersonnelNumber();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPhone();
    LocalDate getDateOfBirth();
    String getProfilePicture();
}

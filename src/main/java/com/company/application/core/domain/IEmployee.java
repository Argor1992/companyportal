package com.company.application.core.domain;

import com.company.application.domain.core.data.IHasId;

import java.time.LocalDate;

public interface IEmployee extends IHasId<Integer> {
    String getPersonnelNumber();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPhone();
    LocalDate getDateOfBirth();
    String getProfilePicture();
}

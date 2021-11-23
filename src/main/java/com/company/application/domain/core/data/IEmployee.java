package com.company.application.domain.core.data;

import java.time.LocalDate;

/**
 * @author Thorsten Zieres, 1297197
 */
public interface IEmployee extends IHasId<Integer> {
    String getPersonnelNumber();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPhone();
    LocalDate getDateOfBirth();
    String getProfilePicture();
}

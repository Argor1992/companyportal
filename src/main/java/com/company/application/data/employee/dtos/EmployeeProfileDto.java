package com.company.application.data.employee.dtos;

import com.company.application.data.address.entity.AddressEntity;
import com.company.application.data.employee.entity.Occupation;
import com.company.application.data.employee.entity.Role;

import java.time.LocalDate;

/**
 * @author Thorsten Zieres, 1297197
 */
public interface EmployeeProfileDto {
    int getId();
    String getPersonnelNumber();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPhone();
    LocalDate getDateOfBirth();
    Occupation getOccupation();
    AddressEntity getAddress();
    Role getRole();
    String getProfilePicture();
}

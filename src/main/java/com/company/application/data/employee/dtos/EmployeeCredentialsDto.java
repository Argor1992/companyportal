package com.company.application.data.employee.dtos;

import com.company.application.data.employee.entity.Role;

/**
 * @author Thorsten Zieres, 1297197
 */
public interface EmployeeCredentialsDto {
    String getEmail();
    String getPassword();
    Role getRole();
}

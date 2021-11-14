package com.company.application.data.employee.dtos;

import com.company.application.data.employee.entity.Role;

public interface EmployeeCredentialsDto {
    String getEmail();
    String getPassword();
    Role getRole();
}

package com.company.application.data.employee.dtos;

import com.company.application.data.employee.entity.Role;

public interface EmployeeCredentialsDTO {
    String getEmail();
    String getPassword();
    Role getRole();
}

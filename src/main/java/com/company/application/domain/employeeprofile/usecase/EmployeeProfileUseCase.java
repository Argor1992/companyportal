package com.company.application.domain.employeeprofile.usecase;

import com.company.application.core.security.SecurityController;
import com.company.application.data.employee.controller.EmployeeController;
import com.company.application.domain.employeeprofile.data.Employee;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeProfileUseCase {
    private final SecurityController securityController;
    private final EmployeeController employeeController;

    public EmployeeProfileUseCase(SecurityController securityController, EmployeeController employeeController) {
        this.securityController = securityController;
        this.employeeController = employeeController;
    }

    public Optional<Employee> getCurrentUser() {
        String name = securityController.getPrincipalName();
        return employeeController.getEmployeeProfile(name);
    }

    public Optional<Employee> getUser(Integer id) {
        return employeeController.getEmployeeProfile(id);
    }
}

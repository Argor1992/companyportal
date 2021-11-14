package com.company.application.domain.employeeprofile.usecase;

import com.company.application.core.security.SecurityController;
import com.company.application.data.employee.controller.EmployeeController;
import com.company.application.domain.employeeprofile.data.EmployeeProfile;
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

    public Optional<EmployeeProfile> getCurrentUser() {
        String name = securityController.getPrincipalName();
        return employeeController.getEmployeeProfile(name);
    }

    public Optional<EmployeeProfile> getUser(Integer id) {
        return employeeController.getEmployeeProfile(id);
    }
}

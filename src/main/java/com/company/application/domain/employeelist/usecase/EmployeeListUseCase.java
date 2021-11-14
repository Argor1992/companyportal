package com.company.application.domain.employeelist.usecase;

import com.company.application.core.security.SecurityController;
import com.company.application.data.employee.controller.EmployeeController;
import com.company.application.data.employee.entity.Occupation;
import com.company.application.data.employee.entity.Role;
import com.company.application.domain.employeelist.data.EmployeeOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeListUseCase {
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private SecurityController securityController;

    public List<EmployeeOverview> getEmployeeList() {
        return employeeController.getEmployeeOverviewList();
    }

    public Optional<EmployeeOverview> getEmployee(int integer) {
        return employeeController.getEmployeeOverview(integer);
    }

    public boolean showUpdateMenu() {
        Optional<Role> role = securityController.getCurrentUserRole();
        Optional<Occupation> occupation = securityController.getCurrentUserOccupation();

        System.err.println(role.get().getUiText());
        System.err.println(occupation.get().getUiText());

        return role.isPresent() && occupation.isPresent() && (role.get() == Role.ADMIN || occupation.get() == Occupation.HUMAN_RESOURCES);
    }
}

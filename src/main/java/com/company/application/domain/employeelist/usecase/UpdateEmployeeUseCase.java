package com.company.application.domain.employeelist.usecase;

import com.company.application.data.employee.controller.EmployeeController;
import com.company.application.domain.employeelist.data.EmployeeOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateEmployeeUseCase {
    @Autowired
    private EmployeeController employeeController;

    public boolean updateEmployee(EmployeeOverview samplePerson) {
        return employeeController.updateEmployee(samplePerson);
    }
}

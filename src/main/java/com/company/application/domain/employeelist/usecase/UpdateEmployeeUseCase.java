package com.company.application.domain.employeelist.usecase;

import com.company.application.data.employee.controller.EmployeeController;
import com.company.application.domain.core.usecase.IUpdateUseCase;
import com.company.application.domain.employeelist.data.EmployeeOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Thorsten Zieres, 1297197
 */
@Service
public class UpdateEmployeeUseCase implements IUpdateUseCase<EmployeeOverview> {
    @Autowired
    private EmployeeController employeeController;

    public boolean update(EmployeeOverview samplePerson) {
        return employeeController.updateEmployee(samplePerson);
    }
}

package com.company.application.domain.employeelist.usecase;

import com.company.application.data.employee.controller.EmployeeController;
import com.company.application.domain.employeelist.data.EmployeeOverview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeListUseCase {
    @Autowired
    private EmployeeController employeeController;

    public List<EmployeeOverview> getEmployeeList() {
        return employeeController.getEmployeeOverviewList();
    }

    public Optional<EmployeeOverview> getEmployee(int integer) {
        return employeeController.getEmployeeOverview(integer);
    }
}

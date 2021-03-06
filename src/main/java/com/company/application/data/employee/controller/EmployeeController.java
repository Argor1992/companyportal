package com.company.application.data.employee.controller;

import com.company.application.data.employee.service.EmployeeService;
import com.company.application.domain.employeelist.data.EmployeeOverview;
import com.company.application.domain.employeeprofile.data.Employee;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

/**
 * @author Thorsten Zieres, 1297197
 */
@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<EmployeeOverview> getEmployeeOverviewList() {
        return employeeService.getEmployeeOverviewList();
    }

    public Optional<EmployeeOverview> getEmployeeOverview(int id) {
        if (id < 1)
            return Optional.empty();
        return employeeService.getEmployeeOverview(id);
    }

    public boolean updateEmployee(EmployeeOverview samplePerson) {
        if (samplePerson == null)
            return false;
        return employeeService.updateEmployee(samplePerson) == 1;
    }

    public Optional<Employee> getEmployeeProfile(String email) {
        if (email == null || email.isEmpty())
            return Optional.empty();
        return employeeService.getEmployeeProfile(email);
    }

    public Optional<Employee> getEmployeeProfile(Integer id) {
        if (id  < 1)
            return Optional.empty();
        return employeeService.getEmployeeProfile(id);
    }
}

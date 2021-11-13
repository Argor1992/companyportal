package com.company.application.data.employee.controller;

import com.company.application.data.employee.service.EmployeeService;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
}

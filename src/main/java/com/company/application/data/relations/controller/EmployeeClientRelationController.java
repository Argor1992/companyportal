package com.company.application.data.relations.controller;

import com.company.application.data.relations.service.EmployeeClientRelationService;
import org.springframework.stereotype.Controller;

/**
 * @author Thorsten Zieres, 1297197
 */
@Controller
public class EmployeeClientRelationController {
    private final EmployeeClientRelationService employeeClientRelationService;

    public EmployeeClientRelationController(EmployeeClientRelationService employeeClientRelationService) {
        this.employeeClientRelationService = employeeClientRelationService;
    }
}

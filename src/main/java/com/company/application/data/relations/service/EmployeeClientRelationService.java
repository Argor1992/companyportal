package com.company.application.data.relations.service;

import com.company.application.data.relations.repository.EmployeeClientRelationRepository;
import org.springframework.stereotype.Service;

@Service
public class EmployeeClientRelationService {
    private final EmployeeClientRelationRepository employeeClientRelationRepository;

    public EmployeeClientRelationService(EmployeeClientRelationRepository employeeClientRelationRepository) {
        this.employeeClientRelationRepository = employeeClientRelationRepository;
    }
}

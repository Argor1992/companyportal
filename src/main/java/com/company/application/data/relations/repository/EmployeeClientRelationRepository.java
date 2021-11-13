package com.company.application.data.relations.repository;

import com.company.application.data.relations.entity.EmployeeClientKey;
import com.company.application.data.relations.entity.EmployeeClientRelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeClientRelationRepository extends JpaRepository<EmployeeClientRelationEntity, EmployeeClientKey> {
}

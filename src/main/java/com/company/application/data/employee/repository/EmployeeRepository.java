package com.company.application.data.employee.repository;

import com.company.application.data.employee.dtos.EmployeeCredentialsDTO;
import com.company.application.data.employee.dtos.EmployeeOverviewDTO;
import com.company.application.data.employee.entity.EmployeeEntity;
import com.company.application.data.employee.entity.Occupation;
import com.company.application.domain.employeelist.data.EmployeeOverview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    Optional<EmployeeCredentialsDTO> findCredentialsByEmail(@Param("email") String email);

    List<EmployeeOverviewDTO> findAllEmployeeOverviewsBy();

    Optional<EmployeeOverviewDTO> findEmployeeOverviewById(@Param("id") Integer id);

    @Modifying
    @Query(
            "update EmployeeEntity set personnelNumber = :personnelNumber, " +
                    "firstName = :firstName, " +
                    "lastName = :lastName, " +
                    "email = :email, " +
                    "phone = :phone, " +
                    "dateOfBirth = :dateOfBirth, " +
                    "occupation = :occupation where lower(username) = lower(:username)"
    )
    int updateUserOverview(@Param("personnelNumber") String personnelNumber,
                           @Param("firstName") String firstName,
                           @Param("lastName") String lastName,
                           @Param("email") String email,
                           @Param("phone")String phone,
                           @Param("dateOfBirth") LocalDate dateOfBirth,
                           @Param("occupation") Occupation occupation);
}
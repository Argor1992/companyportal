package com.company.application.data.employee.repository;

import com.company.application.data.employee.dtos.EmployeeCredentialsDto;
import com.company.application.data.employee.dtos.EmployeeOverviewDto;
import com.company.application.data.employee.dtos.EmployeeProfileDto;
import com.company.application.data.employee.entity.EmployeeEntity;
import com.company.application.data.employee.entity.Occupation;
import com.company.application.data.employee.entity.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {
                    "projects"
            }
    )
    @Query(
            "select e from EmployeeEntity e"
    )
    List<EmployeeEntity> findAllEntitiesAndFetchProjects();

    Optional<EmployeeCredentialsDto> findCredentialsByEmail(@Param("email") String email);

    List<EmployeeOverviewDto> findAllEmployeeOverviewsBy();

    Optional<EmployeeOverviewDto> findEmployeeOverviewById(@Param("id") Integer id);

    Optional<EmployeeProfileDto> findEmployeeProfileByEmail(@Param("email") String email);

    Optional<EmployeeProfileDto> findEmployeeProfileById(@Param("id") Integer id);

    @Query(
            "select e.role from EmployeeEntity e where lower(e.email)=lower(:email)"
    )
    Optional<Role> findEmployeeRoleByEmail(@Param("email") String email);

    @Query(
            "select e.occupation from EmployeeEntity e where lower(e.email)=lower(:email)"
    )
    Optional<Occupation> findEmployeeOccupationByEmail(@Param("email") String email);

    @Modifying
    @Query(
            "update EmployeeEntity set personnelNumber = :personnelNumber, " +
                    "firstName = :firstName, " +
                    "lastName = :lastName, " +
                    "email = :email, " +
                    "phone = :phone, " +
                    "dateOfBirth = :dateOfBirth, " +
                    "occupation = :occupation where id = :id"
    )
    int updateUserOverview(@Param("id") Integer id,
                           @Param("personnelNumber") String personnelNumber,
                           @Param("firstName") String firstName,
                           @Param("lastName") String lastName,
                           @Param("email") String email,
                           @Param("phone") String phone,
                           @Param("dateOfBirth") LocalDate dateOfBirth,
                           @Param("occupation") Occupation occupation);
}
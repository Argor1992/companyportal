package com.company.application.data.employee.service;

import com.company.application.domain.core.data.IClient;
import com.company.application.domain.core.data.IProject;
import com.company.application.data.employee.dtos.EmployeeOverviewDto;
import com.company.application.data.employee.entity.EmployeeEntity;
import com.company.application.data.employee.entity.Occupation;
import com.company.application.data.employee.entity.Role;
import com.company.application.data.employee.repository.EmployeeRepository;
import com.company.application.data.project.entity.ProjectState;
import com.company.application.domain.core.data.Address;
import com.company.application.domain.employeelist.data.EmployeeOverview;
import com.company.application.domain.employeeprofile.data.ClientRelationship;
import com.company.application.domain.employeeprofile.data.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Thorsten Zieres, 1297197
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeOverview> getEmployeeOverviewList() {
        return employeeRepository.findAllEmployeeOverviewsBy().stream().map(this::dtoToEmployeeOverview).collect(Collectors.toList());
    }

    public Optional<EmployeeOverview> getEmployeeOverview(int id) {
        return employeeRepository.findEmployeeOverviewById(id).map(this::dtoToEmployeeOverview);
    }

    @Transactional
    public int updateEmployee(EmployeeOverview samplePerson) {
        return employeeRepository.updateUserOverview(
                samplePerson.getId(),
                samplePerson.getPersonnelNumber(),
                samplePerson.getFirstName(),
                samplePerson.getLastName(),
                samplePerson.getEmail().toLowerCase(),
                samplePerson.getPhone(),
                samplePerson.getDateOfBirth(),
                samplePerson.getOccupation()
        );
    }

    public Optional<Employee> getEmployeeProfile(String email) {
        return employeeRepository.findEmployeeByEmailFetchEagerly(email).map(this::entityToEmployee);
    }

    public Optional<Employee> getEmployeeProfile(Integer id) {
        return employeeRepository.findEmployeeByIdFetchEagerly(id).map(this::entityToEmployee);
    }

    public Optional<Role> getEmployeeRole(String email) {
        if (email == null || email.isEmpty())
            return Optional.empty();
        return employeeRepository.findEmployeeRoleByEmail(email);
    }

    public Optional<Occupation> getEmployeeOccupation(String email) {
        if (email == null || email.isEmpty())
            return Optional.empty();
        return employeeRepository.findEmployeeOccupationByEmail(email);
    }

    private EmployeeOverview dtoToEmployeeOverview(EmployeeOverviewDto employeeOverviewDTO) {
        return new EmployeeOverview(
                employeeOverviewDTO.getId(),
                employeeOverviewDTO.getPersonnelNumber(),
                employeeOverviewDTO.getFirstName(),
                employeeOverviewDTO.getLastName(),
                employeeOverviewDTO.getEmail(),
                employeeOverviewDTO.getPhone(),
                employeeOverviewDTO.getDateOfBirth(),
                employeeOverviewDTO.getOccupation(),
                employeeOverviewDTO.getProfilePicture());
    }

    private Employee entityToEmployee(EmployeeEntity employee) {
        return new Employee(
                employee.getId(),
                employee.getPersonnelNumber(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getDateOfBirth(),
                employee.getOccupation(),
                new Address(
                        employee.getAddress().getStreet(),
                        employee.getAddress().getPostalCode(),
                        employee.getAddress().getCity(),
                        employee.getAddress().getState(),
                        employee.getAddress().getCountry()
                ),
                employee.getRole(),
                employee.getProfilePicture(),
                employee.getClients().stream().map(relation -> new ClientRelationship(
                                new IClient() {
                                    @Override
                                    public Integer getId() { return relation.getClient().getId(); }
                                    @Override
                                    public String getName() { return relation.getClient().getName(); }
                                    @Override
                                    public String getRepresentative() { return relation.getClient().getRepresentative(); }
                                    @Override
                                    public String getEmail() { return relation.getClient().getEmail(); }
                                    @Override
                                    public String getPhone() { return relation.getClient().getPhone(); }
                                },
                                relation.getDate()
                        )
                ).collect(Collectors.toList()),
                employee.getProjects().stream().map(project -> new IProject() {
                    @Override
                    public Integer getId() { return project.getId(); }
                    @Override
                    public String getName() { return project.getName(); }
                    @Override
                    public String getDescription() { return project.getDescription(); }
                    @Override
                    public double getAmount() { return project.getAmount(); }
                    @Override
                    public LocalDate getDate() { return project.getDate(); }
                    @Override
                    public ProjectState getProjectState() { return project.getProjectState(); }
                    @Override
                    public int getPriority() { return project.getPriority(); }
                }).collect(Collectors.toList())
        );
    }
}

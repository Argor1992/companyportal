package com.company.application.data.employee.service;

import com.company.application.data.employee.entity.Occupation;
import com.company.application.data.employee.entity.Role;
import com.company.application.data.employee.repository.EmployeeRepository;
import com.company.application.domain.employeelist.data.EmployeeOverview;
import com.company.application.domain.employeeprofile.data.Address;
import com.company.application.domain.employeeprofile.data.EmployeeProfile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeOverview> getEmployeeOverviewList() {
        return employeeRepository.findAllEmployeeOverviewsBy().stream().map(employeeOverviewDTO -> new EmployeeOverview(
                employeeOverviewDTO.getId(),
                employeeOverviewDTO.getPersonnelNumber(),
                employeeOverviewDTO.getFirstName(),
                employeeOverviewDTO.getLastName(),
                employeeOverviewDTO.getEmail(),
                employeeOverviewDTO.getPhone(),
                employeeOverviewDTO.getDateOfBirth(),
                employeeOverviewDTO.getOccupation()
        )).collect(Collectors.toList());
    }

    public Optional<EmployeeOverview> getEmployeeOverview(int id) {
        return employeeRepository.findEmployeeOverviewById(id).map(employeeOverviewDTO -> new EmployeeOverview(
                employeeOverviewDTO.getId(),
                employeeOverviewDTO.getPersonnelNumber(),
                employeeOverviewDTO.getFirstName(),
                employeeOverviewDTO.getLastName(),
                employeeOverviewDTO.getEmail(),
                employeeOverviewDTO.getPhone(),
                employeeOverviewDTO.getDateOfBirth(),
                employeeOverviewDTO.getOccupation()
        ));
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

    public Optional<EmployeeProfile> getEmployeeProfile(String email) {
        return employeeRepository.findEmployeeProfileByEmail(email).map(employeeProfileDto -> new EmployeeProfile(
                employeeProfileDto.getId(),
                employeeProfileDto.getPersonnelNumber(),
                employeeProfileDto.getFirstName(),
                employeeProfileDto.getLastName(),
                employeeProfileDto.getEmail(),
                employeeProfileDto.getPhone(),
                employeeProfileDto.getDateOfBirth(),
                employeeProfileDto.getOccupation(),
                new Address(
                        employeeProfileDto.getAddress().getStreet(),
                        employeeProfileDto.getAddress().getPostalCode(),
                        employeeProfileDto.getAddress().getCity(),
                        employeeProfileDto.getAddress().getState(),
                        employeeProfileDto.getAddress().getCountry()
                ),
                employeeProfileDto.getRole(),
                employeeProfileDto.getProfilePicture()
        ));
    }

    public Optional<EmployeeProfile> getEmployeeProfile(Integer id) {
        return employeeRepository.findEmployeeProfileById(id).map(employeeProfileDto -> new EmployeeProfile(
                employeeProfileDto.getId(),
                employeeProfileDto.getPersonnelNumber(),
                employeeProfileDto.getFirstName(),
                employeeProfileDto.getLastName(),
                employeeProfileDto.getEmail(),
                employeeProfileDto.getPhone(),
                employeeProfileDto.getDateOfBirth(),
                employeeProfileDto.getOccupation(),
                new Address(
                        employeeProfileDto.getAddress().getStreet(),
                        employeeProfileDto.getAddress().getPostalCode(),
                        employeeProfileDto.getAddress().getCity(),
                        employeeProfileDto.getAddress().getState(),
                        employeeProfileDto.getAddress().getCountry()
                ),
                employeeProfileDto.getRole(),
                employeeProfileDto.getProfilePicture()
        ));
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
}

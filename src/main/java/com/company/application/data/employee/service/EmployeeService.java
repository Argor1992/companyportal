package com.company.application.data.employee.service;

import com.company.application.core.security.SecurityController;
import com.company.application.data.employee.entity.EmployeeEntity;
import com.company.application.data.employee.entity.Occupation;
import com.company.application.data.employee.entity.Role;
import com.company.application.data.employee.repository.EmployeeRepository;
import com.company.application.domain.employeelist.data.EmployeeOverview;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;
    private final SecurityController securityController;

    public EmployeeService(EmployeeRepository repository, SecurityController securityController) {
        this.repository = repository;
        this.securityController = securityController;
    }

    @PostConstruct
    public void createInitialData() {
        if (repository.count() == 0) {
            repository.saveAll(
                    Stream.of(
                            new EmployeeEntity(
                                    "123456", "Eula", "Lane",
                                    "eula.lane@company.de", "100", LocalDate.of(1952, 4, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123457", "Barry", "Rodriquez",
                                    "barry.rodriquez@company.de", "101", LocalDate.of(2011, 4, 9),
                                    Occupation.HUMAN_RESOURCES, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123458", "Eugenia", "Selvi",
                                    "eugenia.selvi@company.de", "102", LocalDate.of(1971, 3, 25),
                                    Occupation.HUMAN_RESOURCES, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123459", "Alejandro", "Miles",
                                    "alejandro.miles@company.de", "103", LocalDate.of(2011, 5, 12),
                                    Occupation.HUMAN_RESOURCES, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123460", "Cora", "Tesi",
                                    "cora.tesi@company.de", "104", LocalDate.of(1969, 7, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123461", "Marguerite", "Ishii",
                                    "marguerite.ishii@company.de", "105", LocalDate.of(1935, 4, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123462", "Mildred", "Jacobs",
                                    "mildred.jacobs@company.de", "106", LocalDate.of(1964, 8, 11),
                                    Occupation.HUMAN_RESOURCES, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123463", "Gene", "Goodman",
                                    "gene.goodman@company.de", "107", LocalDate.of(2000, 9, 19),
                                    Occupation.DEVELOPER, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123464", "Lettie", "Bennett",
                                    "lettie.bennett@company.de", "108", LocalDate.of(1956, 4, 30),
                                    Occupation.DEVELOPER, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123465", "Mabel", "Leach",
                                    "mabel.leach@company.de", "109", LocalDate.of(1990, 1, 8),
                                    Occupation.DEVELOPER, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123466", "Jordan", "Miccinesi",
                                    "jordan.miccinesi@company.de", "110", LocalDate.of(1979, 12, 12),
                                    Occupation.DEVELOPER, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123467", "Marie", "Parkes",
                                    "marie.parkes@company.de", "111", LocalDate.of(1998, 3, 10),
                                    Occupation.DEVELOPER, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123468", "Rose", "Gray",
                                    "rose.gray@company.de", "112", LocalDate.of(1980, 4, 8),
                                    Occupation.SERVICE_DESK, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123469", "Garrett", "Stokes",
                                    "garrett.stokes@company.de", "113", LocalDate.of(1952, 4, 8),
                                    Occupation.DEVELOPER, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123470", "Barbara", "Matthieu",
                                    "barbara.matthieu@company.de", "114", LocalDate.of(1952, 4, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123471", "Jean", "Rhodes",
                                    "jean.rhodes@company.de", "115", LocalDate.of(1952, 4, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123472", "Jack", "Romoli",
                                    "jack.romoli@company.de", "116", LocalDate.of(1952, 4, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123473", "Pearl", "Holden",
                                    "pearl.holden@company.de", "117", LocalDate.of(1952, 4, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123474", "Belle", "Montero",
                                    "belle.montero@company.de", "118", LocalDate.of(1952, 4, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123475", "Olive", "Molina",
                                    "olive.molina@company.de", "119", LocalDate.of(1952, 4, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123476", "Minerva", "Todd",
                                    "minerva.todd@company.de", "120", LocalDate.of(1952, 4, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123477", "Bobby", "Pearson",
                                    "bobby.pearson@company.de", "121", LocalDate.of(1952, 4, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER, securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123478", "Larry", "Ciappi",
                                    "larry.ciappi@company.de", "122", LocalDate.of(1952, 4, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER, securityController.getHashedPassword("test"))
                    ).collect(Collectors.toList())
            );
        }
    }

    public List<EmployeeOverview> getEmployeeOverviewList() {
        return repository.findAllEmployeeOverviewsBy().stream().map(employeeOverviewDTO -> new EmployeeOverview(
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
        return repository.findEmployeeOverviewById(id).map(employeeOverviewDTO -> new EmployeeOverview(
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
        return repository.updateUserOverview(
                samplePerson.getPersonnelNumber(),
                samplePerson.getFirstName(),
                samplePerson.getLastName(),
                samplePerson.getEmail().toLowerCase(),
                samplePerson.getPhone(),
                samplePerson.getDateOfBirth(),
                samplePerson.getOccupation()
        );
    }
}

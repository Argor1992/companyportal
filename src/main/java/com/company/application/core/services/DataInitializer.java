package com.company.application.core.services;

import com.company.application.core.security.SecurityController;
import com.company.application.data.address.entity.AddressEntity;
import com.company.application.data.employee.entity.EmployeeEntity;
import com.company.application.data.employee.entity.Occupation;
import com.company.application.data.employee.entity.Role;
import com.company.application.data.employee.repository.EmployeeRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataInitializer {

    private final EmployeeRepository employeeRepository;
    private final SecurityController securityController;

    public DataInitializer(EmployeeRepository employeeRepository, SecurityController securityController) {
        this.employeeRepository = employeeRepository;
        this.securityController = securityController;
    }

    @PostConstruct
    public void createInitialData() {
        if (employeeRepository.count() == 0) {
            employeeRepository.saveAll(
                    Stream.of(
                            new EmployeeEntity(
                                    "123456", "Eula", "Lane",
                                    "eula.lane@rich.de", "100",
                                    new AddressEntity(
                                            "Musterstraße 1",
                                            "60306",
                                            "Frankfurt",
                                            "Hessen",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER,
                                    "https://randomuser.me/api/portraits/women/1.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123457", "Barry", "Rodriquez",
                                    "barry.rodriquez@rich.de", "101",
                                    new AddressEntity(
                                            "Schoenebergerstrasse 135Musterstraße 1",
                                            "60306",
                                            "Schlema",
                                            "Sachsen",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(2011, 4, 9),
                                    Occupation.HUMAN_RESOURCES, Role.USER,
                                    "https://randomuser.me/api/portraits/men/1.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123458", "Eugenia", "Selvi",
                                    "eugenia.selvi@rich.de", "102",
                                    new AddressEntity(
                                            "Pappelallee 92",
                                            "36466",
                                            "Dermbach",
                                            "Thüringen",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1971, 3, 25),
                                    Occupation.HUMAN_RESOURCES, Role.USER,
                                    "https://randomuser.me/api/portraits/women/2.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123459", "Alejandro", "Miles",
                                    "alejandro.miles@rich.de", "103",
                                    new AddressEntity(
                                            "Paderborner Strasse 87",
                                            "86480",
                                            "Aletshausen",
                                            "Bayern",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(2011, 5, 12),
                                    Occupation.HUMAN_RESOURCES, Role.USER,
                                    "https://randomuser.me/api/portraits/men/2.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123460", "Cora", "Tesi",
                                    "cora.tesi@rich.de", "104",
                                    new AddressEntity(
                                            "Sonnenallee 55",
                                            "86034",
                                            "Augsburg",
                                            "Bayern",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1969, 7, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER,
                                    "https://randomuser.me/api/portraits/women/3.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123461", "Marguerite", "Ishii",
                                    "marguerite.ishii@rich.de", "105",
                                    new AddressEntity(
                                            "Fugger Strasse 24",
                                            "14407",
                                            "Potsdam",
                                            "Brandenburg",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1935, 4, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER,
                                    "https://randomuser.me/api/portraits/women/4.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123462", "Mildred", "Jacobs",
                                    "mildred.jacobs@rich.de", "106",
                                    new AddressEntity(
                                            "Kantstraße 79",
                                            "08203",
                                            "Auerbach",
                                            "Sachsen",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1964, 8, 11),
                                    Occupation.HUMAN_RESOURCES, Role.USER,
                                    "https://randomuser.me/api/portraits/women/5.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123463", "Gene", "Goodman",
                                    "gene.goodman@rich.de", "107",
                                    new AddressEntity(
                                            "Rhinstrasse 110",
                                            "80719",
                                            "München",
                                            "Bayern",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(2000, 9, 19),
                                    Occupation.DEVELOPER, Role.USER,
                                    "https://randomuser.me/api/portraits/men/3.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123464", "Lettie", "Bennett",
                                    "lettie.bennett@rich.de", "108",
                                    new AddressEntity(
                                            "Eschenweg 69",
                                            "07717",
                                            "Jena",
                                            "Thüringen",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1956, 4, 30),
                                    Occupation.DEVELOPER, Role.USER,
                                    "https://randomuser.me/api/portraits/women/6.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123465", "Mabel", "Leach",
                                    "mabel.leach@rich.de", "109",
                                    new AddressEntity(
                                            "Kantstraße 85",
                                            "09204",
                                            "Limbach-Oberfrohna",
                                            "Sachsen",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1990, 1, 8),
                                    Occupation.DEVELOPER, Role.USER,
                                    "https://randomuser.me/api/portraits/women/7.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123466", "Jordan", "Miccinesi",
                                    "jordan.miccinesi@rich.de", "110",
                                    new AddressEntity(
                                            "Augsburger Straße 110",
                                            "58513",
                                            "Lüdenscheid",
                                            "Nordrhein-Westfalen",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1979, 12, 12),
                                    Occupation.DEVELOPER, Role.USER,
                                    "https://randomuser.me/api/portraits/men/4.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123467", "Mark", "Parkes",
                                    "marie.parkes@rich.de", "111",
                                    new AddressEntity(
                                            "Flughafenstrasse 22",
                                            "92714",
                                            "Pleystein",
                                            "Bayern",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1998, 3, 10),
                                    Occupation.DEVELOPER, Role.USER,
                                    "https://randomuser.me/api/portraits/men/5.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123468", "Rose", "Gray",
                                    "rose.gray@rich.de", "112",
                                    new AddressEntity(
                                            "Chausseestr. 28",
                                            "25408",
                                            "Pinneberg",
                                            "Schleswig-Holstein",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1980, 4, 8),
                                    Occupation.SERVICE_DESK, Role.USER,
                                    "https://randomuser.me/api/portraits/women/8.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123469", "Garrett", "Stokes",
                                    "garrett.stokes@rich.de", "113",
                                    new AddressEntity(
                                            "Rudower Strasse 73",
                                            "54597",
                                            "Rommersheim",
                                            "Rheinland-Pfalz",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.DEVELOPER, Role.USER,
                                    "https://randomuser.me/api/portraits/men/6.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123470", "Bob", "Matthieu",
                                    "barbara.matthieu@rich.de", "114",
                                    new AddressEntity(
                                            "Brandenburgische Str 83",
                                            "55568",
                                            "Abtweiler",
                                            "Rheinland-Pfalz",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.MANAGEMENT, Role.ADMIN,
                                    "https://randomuser.me/api/portraits/men/7.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123471", "Jean", "Rhodes",
                                    "jean.rhodes@rich.de", "115",
                                    new AddressEntity(
                                            "Ellmenreichstrasse 124",
                                            "91364",
                                            "Unterleinleiter",
                                            "Bayern",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.MANAGEMENT, Role.ADMIN,
                                    "https://randomuser.me/api/portraits/men/8.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123472", "Jack", "Romoli",
                                    "jack.romoli@rich.de", "116",
                                    new AddressEntity(
                                            "Charlottenstrasse 104",
                                            "01261",
                                            "Dresden",
                                            "Sachsen",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.MANAGEMENT, Role.ADMIN,
                                    "https://randomuser.me/api/portraits/men/9.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123473", "Pearl", "Holden",
                                    "pearl.holden@rich.de", "117",
                                    new AddressEntity(
                                            "Hochstrasse 81",
                                            "25818",
                                            "Bredstedt",
                                            "Schleswig-Holstein",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.SALES, Role.USER,
                                    "https://randomuser.me/api/portraits/women/9.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123474", "Belle", "Montero",
                                    "belle.montero@rich.de", "118",
                                    new AddressEntity(
                                            "Augsburger Strasse 92",
                                            "56767",
                                            "Kolverath",
                                            "Rheinland-Pfalz",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.SALES, Role.USER,
                                    "https://randomuser.me/api/portraits/women/10.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123475", "Olive", "Molina",
                                    "olive.molina@rich.de", "119",
                                    new AddressEntity(
                                            "Rohrdamm 72",
                                            "31093",
                                            "Hoyershausen",
                                            "Niedersachsen",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.SALES, Role.USER,
                                    "https://randomuser.me/api/portraits/women/11.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123476", "Minerva", "Todd",
                                    "minerva.todd@rich.de", "120",
                                    new AddressEntity(
                                            "Joachimstaler Str. 36",
                                            "55496",
                                            "Argenthal",
                                            "Rheinland-Pfalz",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.SALES, Role.USER,
                                    "https://randomuser.me/api/portraits/women/12.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123477", "Bobby", "Pearson",
                                    "bobby.pearson@rich.de", "121",
                                    new AddressEntity(
                                            "Am Borsigturm 85",
                                            "41541",
                                            "Stürzelberg",
                                            "Nordrhein-Westfalen",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.SALES, Role.USER,
                                    "https://randomuser.me/api/portraits/men/10.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123478", "Larry", "Ciappi",
                                    "larry.ciappi@rich.de", "122",
                                    new AddressEntity(
                                            "Langenhorner Chaussee 66",
                                            "83313",
                                            "Grub",
                                            "Bayern",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.SALES, Role.USER,
                                    "https://randomuser.me/api/portraits/men/11.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123479", "Ronnie", "Salucci",
                                    "ronnie.salucci@rich.de", "123",
                                    new AddressEntity(
                                            "Scharnweberstrasse 58",
                                            "65817",
                                            "Eppstein",
                                            "Hessen",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1979, 4, 3),
                                    Occupation.SERVICE_DESK, Role.USER,
                                    "https://randomuser.me/api/portraits/men/12.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123480", "Walter", "Grossi",
                                    "walter.grossi@rich.de", "124",
                                    new AddressEntity(
                                            "Genterstrasse 85",
                                            "24032",
                                            "Kiel",
                                            "Schleswig-Holstein",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1979, 5, 21),
                                    Occupation.SERVICE_DESK, Role.USER,
                                    "https://randomuser.me/api/portraits/men/13.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123481", "Frances", "Koopmans",
                                    "frances.koopmans@rich.de", "125",
                                    new AddressEntity(
                                            "Rohrdamm 40",
                                            "29693",
                                            "Hademstorf",
                                            "Niedersachsen",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1979, 7, 13),
                                    Occupation.SERVICE_DESK, Role.USER,
                                    "https://randomuser.me/api/portraits/men/14.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123482", "Frances", "Fujimoto",
                                    "frances.fujimoto@rich.de", "126",
                                    new AddressEntity(
                                            "Alt Reinickendorf 71",
                                            "86672",
                                            "Thierhaupten",
                                            "Bayern",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1979, 12, 16),
                                    Occupation.SERVICE_DESK, Role.USER,
                                    "https://randomuser.me/api/portraits/men/15.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123483", "Olivia", "Vidal",
                                    "olivia.vidal@rich.de", "127",
                                    new AddressEntity(
                                            "Friedrichstrasse 127",
                                            "40213",
                                            "Karlstadt",
                                            "Nordrhein-Westfalen",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1979, 3, 26),
                                    Occupation.SERVICE_DESK, Role.USER,
                                    "https://randomuser.me/api/portraits/women/13.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123484", "Edna", "Henry",
                                    "edna.henry@rich.de", "128",
                                    new AddressEntity(
                                            "Hollander Strasse 89",
                                            "65623",
                                            "Netzbach",
                                            "Rheinland-Pfalz",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1944, 7, 2),
                                    Occupation.MARKETING, Role.USER,
                                    "https://randomuser.me/api/portraits/women/14.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123485", "Lydia", "Brun",
                                    "lydia.brun@rich.de", "129",
                                    new AddressEntity(
                                            "Esplanade 46",
                                            "85619",
                                            "Feldkirchen",
                                            "Bayern",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1988, 3, 23),
                                    Occupation.MARKETING, Role.USER,
                                    "https://randomuser.me/api/portraits/women/15.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123486", "Jay", "Blake",
                                    "jay.blake@rich.de", "130",
                                    new AddressEntity(
                                            "Fontenay 55",
                                            "95620",
                                            "Wunsiedel",
                                            "Bayern",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(2000, 9, 4),
                                    Occupation.MARKETING, Role.USER,
                                    "https://randomuser.me/api/portraits/men/16.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    "123487", "Isabel", "Serafini",
                                    "isabel.serafini@rich.de", "131",
                                    new AddressEntity(
                                            "Kastanienallee 119",
                                            "24590",
                                            "Hohenwestedt",
                                            "Schleswig-Holstein",
                                            "Deutschland"
                                    ),
                                    LocalDate.of(1999, 1, 17),
                                    Occupation.MARKETING, Role.USER,
                                    "https://randomuser.me/api/portraits/women/16.jpg",
                                    securityController.getHashedPassword("test"))
                    ).collect(Collectors.toList())
            );
        }
    }
}

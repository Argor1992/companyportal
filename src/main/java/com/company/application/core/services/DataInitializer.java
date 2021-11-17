package com.company.application.core.services;

import com.company.application.core.security.SecurityController;
import com.company.application.data.address.entity.AddressEntity;
import com.company.application.data.client.entity.ClientEntity;
import com.company.application.data.client.repository.ClientRepository;
import com.company.application.data.employee.entity.EmployeeEntity;
import com.company.application.data.employee.entity.Occupation;
import com.company.application.data.employee.entity.Role;
import com.company.application.data.employee.repository.EmployeeRepository;
import com.company.application.data.project.entity.ProjectEntity;
import com.company.application.data.project.entity.ProjectState;
import com.company.application.data.project.repository.ProjectRepository;
import com.company.application.data.relations.entity.EmployeeClientRelationEntity;
import com.company.application.data.relations.repository.EmployeeClientRelationRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DataInitializer {

    private final EmployeeRepository employeeRepository;
    private final ClientRepository clientRepository;
    private final EmployeeClientRelationRepository relationRepository;
    private final ProjectRepository projectRepository;
    private final SecurityController securityController;

    public DataInitializer(EmployeeRepository employeeRepository, ClientRepository clientRepository, EmployeeClientRelationRepository relationRepository, ProjectRepository projectRepository, SecurityController securityController) {
        this.employeeRepository = employeeRepository;
        this.clientRepository = clientRepository;
        this.relationRepository = relationRepository;
        this.projectRepository = projectRepository;
        this.securityController = securityController;
    }

    @PostConstruct
    public void createInitialData() {
        createInitialEmployeeData();
        createInitialClientData();
        createInitialRelationData();
        createInitialProjectData();
    }

    private void createInitialEmployeeData() {
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
                                            "Schoenebergerstrasse 135",
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
                                    "mark.parkes@rich.de", "111",
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

    private void createInitialClientData() {
        if (clientRepository.count() == 0) {
            clientRepository.saveAll(
                    Stream.of(
                            new ClientEntity(
                                    "Ruecker LLC",
                                    "Katrin W Schulz",
                                    "schulz@ruecker.de",
                                    "08442 54 65869",
                                    new AddressEntity(
                                            "Rankestraße 28",
                                            "85280",
                                            "Wolnzach",
                                            "Bayern",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Treutel, Hirthe and Ortiz",
                                    "Uwe J Schuster",
                                    "schuster@treutel.de",
                                    "06340 71 37226",
                                    new AddressEntity(
                                            "Brandenburgische Strasse 78",
                                            "76872",
                                            "Freckenfeld",
                                            "Rheinland-Pfalz",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Heller - Schuster",
                                    "Klaudia N Lang",
                                    "lang@heller-schuster.de",
                                    "04353 19 63106",
                                    new AddressEntity(
                                            "Schönwalder Allee 112",
                                            "24361",
                                            "Damendorf",
                                            "Schleswig-Holstein",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Dach, Prosacco and Nolan",
                                    "Michael K Fischer",
                                    "fischer@dach.de",
                                    "03631 32 60420",
                                    new AddressEntity(
                                            "Heinrich Heine Platz 127",
                                            "86574",
                                            "Petersdorf",
                                            "Bayern",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Jacobs, Olson and Baumbach",
                                    "Felix A Lehrer",
                                    "lehrer@jacobs.de",
                                    "033232 15972",
                                    new AddressEntity(
                                            "Schmarjestrasse 114",
                                            "14652",
                                            "Brieselang",
                                            "Brandenburg",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Champlin - Crona",
                                    "Leonie P Blau",
                                    "blau@champlon-crona.de",
                                    "09632 55 84754",
                                    new AddressEntity(
                                            "Flughafenstrasse 71",
                                            "17111",
                                            "Aalbude",
                                            "Mecklenburg-Vorpommern",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Shanahan Inc",
                                    "Mathias K Shuster",
                                    "shuster@shanahan.de",
                                    "04107 30 86464",
                                    new AddressEntity(
                                            "Inge Beisheim Platz 120",
                                            "22962",
                                            "Siek",
                                            "Schleswig-Holstein",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Wolf, Armstrong and Harris",
                                    "Daniel S Drescher",
                                    "drescher@wolf.de",
                                    "034443 92100",
                                    new AddressEntity(
                                            "Hoheluftchaussee 107",
                                            "06680",
                                            "Teuchern",
                                            "Sachsen-Anhalt",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Donnelly - Kovacek",
                                    "Tobias M Pfeiffer",
                                    "pfeiffer@donnelly.de",
                                    "09861 59 66825",
                                    new AddressEntity(
                                            "Heiligengeistbrücke 73",
                                            "91536",
                                            "Rothenburg",
                                            "Bayern",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Nader Group",
                                    "Sandra T Schmidt",
                                    "schmidt@nadergroup.de",
                                    "03641 49 20151",
                                    new AddressEntity(
                                            "Eschenweg 64",
                                            "07707",
                                            "Jena",
                                            "Thüringen",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Schinner and Sons",
                                    "Christine S Gaertner",
                                    "gaertner@schinner.de",
                                    "06074 29 80824",
                                    new AddressEntity(
                                            "Konstanzer Strasse 56",
                                            "63303",
                                            "Dreieich",
                                            "Hessen",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Bergstrom, Larson and Skiles",
                                    "Kerstin S Probst",
                                    "probst@bergstrom.de",
                                    "0365 39 68582",
                                    new AddressEntity(
                                            "Gotthardstrasse 16",
                                            "07515",
                                            "Gera",
                                            "Thüringen",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Borer, Reichert and Schmeler",
                                    "Heike R Nadel",
                                    "nadel@borer.de",
                                    "05221 96 45894",
                                    new AddressEntity(
                                            "Rohrdamm 78",
                                            "32052",
                                            "Innenstadt",
                                            "Nordrhein-Westfalen",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Labadie Inc",
                                    "Jana T Kaufmann",
                                    "kaufmann@labadie.de",
                                    "04533 96 51908",
                                    new AddressEntity(
                                            "Hans-Grade-Allee 41",
                                            "23854",
                                            "Reinfeld",
                                            "Schleswig-Holstein",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Hackett, Cormier and Nader",
                                    "Michael A Frei",
                                    "frei@hackett.de",
                                    "02682 99 31040",
                                    new AddressEntity(
                                            "Knesebeckstrasse 117",
                                            "03141",
                                            "Forst",
                                            "Brandenburg",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Kirlin - Gleason",
                                    "Tom A Fleischer",
                                    "fleischer@kirlin-gleason.de",
                                    "09303 14 26465",
                                    new AddressEntity(
                                            "Billwerder Neuer Deich 76",
                                            "97235",
                                            "Randersacker",
                                            "Bayern",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Weber, Heller and Bauch",
                                    "Sebastian G Maurer",
                                    "maurer@weber.de",
                                    "02691 82 12505",
                                    new AddressEntity(
                                            "Augsburger Strasse 93",
                                            "53534",
                                            "Wirft",
                                            "Rheinland-Pfalz",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Hessel, Hayes and Witting",
                                    "Dieter A Reinhard",
                                    "reinhard@hessel.de",
                                    "07248 80 76559",
                                    new AddressEntity(
                                            "Sömmeringstr. 21",
                                            "76359",
                                            "Marxzell",
                                            "Baden-Württemberg",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Herman LLC",
                                    "Lisa L Rothschild",
                                    "rothschild@hemanllc.de",
                                    "02688 63 41765",
                                    new AddressEntity(
                                            "Knesebeckstrasse 52",
                                            "57627",
                                            "Marzhausen",
                                            "Rheinland-Pfalz",
                                            "Deutschland"
                                    )
                            ),
                            new ClientEntity(
                                    "Runolfsdottir - Homenick",
                                    "Philipp A Duerr",
                                    "duerr@runolfsdottor.de",
                                    "09621 72 42107",
                                    new AddressEntity(
                                            "Flughafenstrasse 46",
                                            "92256",
                                            "Hahnbach",
                                            "Bayern",
                                            "Deutschland"
                                    )
                            )
                    ).collect(Collectors.toList())
            );
        }
    }

    private void createInitialRelationData() {
        if (relationRepository.count() == 0) {
            List<EmployeeEntity> employees = employeeRepository.findAll();
            List<ClientEntity> clientEntities = clientRepository.findAll();
            SecureRandom rnd = new SecureRandom();

            employees.forEach(employeeEntity -> {
                for (int i = 0; i < 3; i++) {
                    relationRepository.save(
                            new EmployeeClientRelationEntity(
                                    employeeEntity,
                                    clientEntities.get(rnd.nextInt(clientEntities.size()-1)),
                                    LocalDate.of(rnd.nextInt(20) + 1980, rnd.nextInt(11) + 1, 1)
                            )
                    );
                }
            });
        }
    }

    private void createInitialProjectData() {
        if (projectRepository.count() == 0) {
            List<EmployeeEntity> employees = employeeRepository.findAllEntitiesAndFetchProjects();
            List<ClientEntity> clientEntities = clientRepository.findAllEntitiesAndFetchProjects();
            String[] names = new String[]{"PAINSTAKING WRENCH", "FUNCTIONAL BIRTHDAY", "CHEAP PLEASURE",
                    "HIGH SHOES", "VICTORIOUS NECK", "USEFUL WAVES", "JADED TEETH", "HULKING PLOT",
                    "HIGHFALUTIN DRINK", "RIPE TOUCH", "HOMELY CROOK", "TRUCULENT FANG", "WISE DESK",
                    "OVERWROUGHT MOUTH", "WISTFUL WING", "GRAND INSTRUMENT", "UBIQUITOUS HALL",
                    "NEXT CROWD", "FLUTTERING MIDDLE", "SHALLOW REASON", "COORDINATED OCEAN",
                    "ACCEPTABLE HOT", "EXCLUSIVE STAGE", "BURLY WINE", "ENCOURAGING LINE",
                    "WORTHY PAPER", "INTERNAL SAND", "ABSTRACTED SYSTEM", "HELPLESS SUGAR",
                    "WATERY JEANS", "STRAIGHT COW", "QUACK LABORER", "WEARY WIRE", "ABSENT AUNT",
                    "LAST SUBSTANCE", "GRANDIOSE TIGER", "ROOMY POT", "COORDINATED THROAT",
                    "ASTONISHING WORK", "IMMUNE FIELD"};
            SecureRandom rnd = new SecureRandom();

            System.err.println(employees.size());
            System.err.println(clientEntities.size());

            for (String name : names) {
                ProjectEntity projectEntity = new ProjectEntity(
                        name,
                        "This project is very important because it brings a lot of money.",
                        rnd.nextInt(1_000_000) + 10000,
                        LocalDate.of(rnd.nextInt(20) + 1980, rnd.nextInt(11) + 1, 1),
                        ProjectState.values()[rnd.nextInt(ProjectState.values().length)],
                        rnd.nextInt(10)
                );

                projectRepository.save(projectEntity);
            }

            createInitialProjectContributors();
        }
    }

    private void createInitialProjectContributors() {
        List<EmployeeEntity> employees = employeeRepository.findAllEntitiesAndFetchProjects();
        List<ClientEntity> clients = clientRepository.findAllEntitiesAndFetchProjects();
        List<ProjectEntity> projects = projectRepository.findAllEntitiesAndFetchProjects();
        SecureRandom rnd = new SecureRandom();

        projects.forEach(
                projectEntity -> {
                    List.of(employees.get(rnd.nextInt(employees.size() - 1)), employees.get(rnd.nextInt(employees.size() - 1))).forEach(
                            projectEntity::addProjectManager
                    );

                    List.of(clients.get(rnd.nextInt(clients.size() - 1)), clients.get(rnd.nextInt(clients.size() - 1))).forEach(
                            projectEntity::addProjectClient
                    );

                    projectRepository.save(projectEntity);
                }
        );
    }


}

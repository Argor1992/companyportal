package com.company.application.core.services;

import com.company.application.core.security.SecurityController;
import com.company.application.data.address.entity.AddressEntity;
import com.company.application.data.address.entity.City;
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

/**
 * @author Thorsten Zieres, 1297197
 */
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
            City city = new City("Frankfurt am Main", "60313", "Hessen", "Deutschland");
            City city2 = new City("Bad Vilbel", "61118", "Hessen", "Deutschland");
            City city3 = new City("Bad Homburg", "61348", "Hessen", "Deutschland");

            int personnelNumber = 123456;
            int telephone = 100;

            String portraitLink = "https://randomuser.me/api/portraits/";
            String email = "@rich.de";
            employeeRepository.saveAll(
                    Stream.of(
                            new EmployeeEntity(
                                    personnelNumber++, "Chadrick", "Cantwell",
                                    "chadrick.cantwell" + email, telephone++,
                                    new AddressEntity("Schöne Aussicht 1", city3),
                                    LocalDate.of(1992, 12, 31),
                                    Occupation.MARKETING, Role.USER,
                                    "images/chadrick.PNG",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Eula", "Lane",
                                    "eula.lane" + email, telephone++,
                                    new AddressEntity("Musterstraße 1", city),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER,
                                    portraitLink + "women/1.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Daniel", "Jung",
                                    "barry.rodriquez" + email, telephone++,
                                    new AddressEntity("Schoenebergerstrasse 135", city2),
                                    LocalDate.of(2011, 4, 9),
                                    Occupation.IT_SECURITY, Role.USER,
                                    portraitLink + "men/1.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Eugenia", "Selvi",
                                    "eugenia.selvi" + email, telephone++,
                                    new AddressEntity("Pappelallee 92", city3),
                                    LocalDate.of(1971, 3, 25),
                                    Occupation.HUMAN_RESOURCES, Role.USER,
                                    portraitLink + "women/2.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Rick", "Stanley",
                                    "stanley-rick" + email, telephone++,
                                    new AddressEntity("Paderborner Strasse 87", city),
                                    LocalDate.of(2011, 5, 12),
                                    Occupation.IT_SECURITY, Role.USER,
                                    portraitLink + "men/2.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Cora", "Tesi",
                                    "cora.tesi" + email, telephone++,
                                    new AddressEntity("Sonnenallee 55", city2),
                                    LocalDate.of(1969, 7, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER,
                                    portraitLink + "women/3.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Marguerite", "Ishii",
                                    "marguerite.ishii" + email, telephone++,
                                    new AddressEntity("Fugger Strasse 24", city3),
                                    LocalDate.of(1935, 4, 8),
                                    Occupation.HUMAN_RESOURCES, Role.USER,
                                    portraitLink + "women/4.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Jochen", "Günther",
                                    "mildred.jacobs" + email, telephone++,
                                    new AddressEntity("Kantstraße 79", city),
                                    LocalDate.of(1964, 8, 11),
                                    Occupation.HUMAN_RESOURCES, Role.USER,
                                    portraitLink + "men/50.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Gene", "Goodman",
                                    "gene.goodman" + email, telephone++,
                                    new AddressEntity("Rhinstrasse 110", city2),
                                    LocalDate.of(2000, 9, 19),
                                    Occupation.DEVELOPER, Role.USER,
                                    portraitLink + "men/3.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Lettie", "Bennett",
                                    "lettie.bennett" + email, telephone++,
                                    new AddressEntity("Eschenweg 69", city3),
                                    LocalDate.of(1956, 4, 30),
                                    Occupation.DEVELOPER, Role.USER,
                                    portraitLink + "women/6.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Mabel", "Leach",
                                    "mabel.leach" + email, telephone++,
                                    new AddressEntity("Kantstraße 85", city),
                                    LocalDate.of(1990, 1, 8),
                                    Occupation.DEVELOPER, Role.USER,
                                    portraitLink + "women/7.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Jordan", "Miccinesi",
                                    "jordan.miccinesi" + email, telephone++,
                                    new AddressEntity("Augsburger Straße 110", city2),
                                    LocalDate.of(1979, 12, 12),
                                    Occupation.DEVELOPER, Role.USER,
                                    portraitLink + "men/4.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Mark", "Parkes",
                                    "mark.parkes" + email, telephone++,
                                    new AddressEntity("Flughafenstrasse 22", city3),
                                    LocalDate.of(1998, 3, 10),
                                    Occupation.DEVELOPER, Role.USER,
                                    portraitLink + "men/5.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Rose", "Gray",
                                    "rose.gray" + email, telephone++,
                                    new AddressEntity("Chausseestr. 28", city),
                                    LocalDate.of(1980, 4, 8),
                                    Occupation.SERVICE_DESK, Role.USER,
                                    portraitLink + "women/8.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Garrett", "Stokes",
                                    "garrett.stokes" + email, telephone++,
                                    new AddressEntity("Rudower Strasse 73", city2),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.DEVELOPER, Role.USER,
                                    portraitLink + "men/6.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Bob", "Matthieu",
                                    "bob.matthieu" + email, telephone++,
                                    new AddressEntity("Brandenburgische Str 83", city3),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.MANAGEMENT, Role.ADMIN,
                                    portraitLink + "men/7.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Jean", "Rhodes",
                                    "jean.rhodes" + email, telephone++,
                                    new AddressEntity("Ellmenreichstrasse 124", city),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.MANAGEMENT, Role.ADMIN,
                                    portraitLink + "men/8.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Jack", "Romoli",
                                    "jack.romoli" + email, telephone++,
                                    new AddressEntity("Charlottenstrasse 104", city2),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.MANAGEMENT, Role.ADMIN,
                                    portraitLink + "men/9.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Pearl", "Holden",
                                    "pearl.holden" + email, telephone++,
                                    new AddressEntity("Hochstrasse 81", city3),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.SALES, Role.USER,
                                    portraitLink + "women/9.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Belle", "Montero",
                                    "belle.montero" + email, telephone++,
                                    new AddressEntity("Augsburger Strasse 92", city),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.SALES, Role.USER,
                                    portraitLink + "women/10.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Olive", "Molina",
                                    "olive.molina" + email, telephone++,
                                    new AddressEntity("Rohrdamm 72", city),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.SALES, Role.USER,
                                    portraitLink + "women/11.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Minerva", "Todd",
                                    "minerva.todd" + email, telephone++,
                                    new AddressEntity("Joachimstaler Str. 36", city),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.SALES, Role.USER,
                                    portraitLink + "women/12.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Bobby", "Pearson",
                                    "bobby.pearson" + email, telephone++,
                                    new AddressEntity("Am Borsigturm 85", city),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.SALES, Role.USER,
                                    portraitLink + "men/10.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Larry", "Ciappi",
                                    "larry.ciappi" + email, telephone++,
                                    new AddressEntity("Langenhorner Chaussee 66", city),
                                    LocalDate.of(1952, 4, 8),
                                    Occupation.SALES, Role.USER,
                                    portraitLink + "men/11.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Ronnie", "Salucci",
                                    "ronnie.salucci" + email, telephone++,
                                    new AddressEntity("Scharnweberstrasse 58", city2),
                                    LocalDate.of(1979, 4, 3),
                                    Occupation.SERVICE_DESK, Role.USER,
                                    portraitLink + "men/12.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Walter", "Grossi",
                                    "walter.grossi" + email, telephone++,
                                    new AddressEntity("Genterstrasse 85", city3),
                                    LocalDate.of(1979, 5, 21),
                                    Occupation.SERVICE_DESK, Role.USER,
                                    portraitLink + "men/13.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Frances", "Koopmans",
                                    "frances.koopmans" + email, telephone++,
                                    new AddressEntity("Rohrdamm 40", city2),
                                    LocalDate.of(1979, 7, 13),
                                    Occupation.SERVICE_DESK, Role.USER,
                                    portraitLink + "men/14.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Frances", "Fujimoto",
                                    "frances.fujimoto" + email, telephone++,
                                    new AddressEntity("Alt Reinickendorf 71", city),
                                    LocalDate.of(1979, 12, 16),
                                    Occupation.SERVICE_DESK, Role.USER,
                                    portraitLink + "men/15.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Olivia", "Vidal",
                                    "olivia.vidal" + email, telephone++,
                                    new AddressEntity("Friedrichstrasse 127", city),
                                    LocalDate.of(1979, 3, 26),
                                    Occupation.SERVICE_DESK, Role.USER,
                                    portraitLink + "women/13.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Edna", "Henry",
                                    "edna.henry" + email, telephone++,
                                    new AddressEntity("Hollander Strasse 89", city2),
                                    LocalDate.of(1944, 7, 2),
                                    Occupation.MARKETING, Role.USER,
                                    portraitLink + "women/14.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Lydia", "Brun",
                                    "lydia.brun" + email, telephone++,
                                    new AddressEntity("Esplanade 46", city3),
                                    LocalDate.of(1988, 3, 23),
                                    Occupation.MARKETING, Role.USER,
                                    portraitLink + "women/15.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber++, "Jay", "Blake",
                                    "jay.blake" + email, telephone++,
                                    new AddressEntity("Fontenay 55", city),
                                    LocalDate.of(2000, 9, 4),
                                    Occupation.MARKETING, Role.USER,
                                    portraitLink + "men/16.jpg",
                                    securityController.getHashedPassword("test")),
                            new EmployeeEntity(
                                    personnelNumber, "Isabel", "Serafini",
                                    "isabel.serafini" + email, telephone,
                                    new AddressEntity("Kastanienallee 119", city2),
                                    LocalDate.of(1999, 1, 17),
                                    Occupation.MARKETING, Role.USER,
                                    portraitLink + "women/16.jpg",
                                    securityController.getHashedPassword("test"))
                    ).collect(Collectors.toList())
            );
        }
    }

    private void createInitialClientData() {
        if (clientRepository.count() == 0) {
            City city = new City("Frankfurt am Main", "60313", "Hessen", "Deutschland");
            City city2 = new City("Bad Vilbel", "61118", "Hessen", "Deutschland");
            City city3 = new City("Bad Homburg", "61348", "Hessen", "Deutschland");

            clientRepository.saveAll(
                    Stream.of(
                            new ClientEntity(
                                    "Ruecker LLC",
                                    "Katrin W Schulz",
                                    "schulz@ruecker.de",
                                    "08442 54 65869",
                                    new AddressEntity("Rankestraße 28", city)
                            ),
                            new ClientEntity(
                                    "Treutel, Hirthe and Ortiz",
                                    "Uwe J Schuster",
                                    "schuster@treutel.de",
                                    "06340 71 37226",
                                    new AddressEntity("Brandenburgische Strasse 78", city2)
                            ),
                            new ClientEntity(
                                    "Heller - Schuster",
                                    "Klaudia N Lang",
                                    "lang@heller-schuster.de",
                                    "04353 19 63106",
                                    new AddressEntity("Schönwalder Allee 112", city3)
                            ),
                            new ClientEntity(
                                    "Dach, Prosacco and Nolan",
                                    "Michael K Fischer",
                                    "fischer@dach.de",
                                    "03631 32 60420",
                                    new AddressEntity("Heinrich Heine Platz 127", city)
                            ),
                            new ClientEntity(
                                    "Jacobs, Olson and Baumbach",
                                    "Felix A Lehrer",
                                    "lehrer@jacobs.de",
                                    "033232 15972",
                                    new AddressEntity("Schmarjestrasse 114", city)
                            ),
                            new ClientEntity(
                                    "Champlin - Crona",
                                    "Leonie P Blau",
                                    "blau@champlon-crona.de",
                                    "09632 55 84754",
                                    new AddressEntity("Flughafenstrasse 71", city2)
                            ),
                            new ClientEntity(
                                    "Shanahan Inc",
                                    "Mathias K Shuster",
                                    "shuster@shanahan.de",
                                    "04107 30 86464",
                                    new AddressEntity("Inge Beisheim Platz 120", city3)
                            ),
                            new ClientEntity(
                                    "Wolf, Armstrong and Harris",
                                    "Daniel S Drescher",
                                    "drescher@wolf.de",
                                    "034443 92100",
                                    new AddressEntity("Hoheluftchaussee 107", city)
                            ),
                            new ClientEntity(
                                    "Donnelly - Kovacek",
                                    "Tobias M Pfeiffer",
                                    "pfeiffer@donnelly.de",
                                    "09861 59 66825",
                                    new AddressEntity("Heiligengeistbrücke 73", city)
                            ),
                            new ClientEntity(
                                    "Nader Group",
                                    "Sandra T Schmidt",
                                    "schmidt@nadergroup.de",
                                    "03641 49 20151",
                                    new AddressEntity("Eschenweg 64", city)
                            ),
                            new ClientEntity(
                                    "Schinner and Sons",
                                    "Christine S Gaertner",
                                    "gaertner@schinner.de",
                                    "06074 29 80824",
                                    new AddressEntity("Konstanzer Strasse 56", city2)
                            ),
                            new ClientEntity(
                                    "Bergstrom, Larson and Skiles",
                                    "Kerstin S Probst",
                                    "probst@bergstrom.de",
                                    "0365 39 68582",
                                    new AddressEntity("Gotthardstrasse 16", city3)
                            ),
                            new ClientEntity(
                                    "Borer, Reichert and Schmeler",
                                    "Heike R Nadel",
                                    "nadel@borer.de",
                                    "05221 96 45894",
                                    new AddressEntity("Rohrdamm 78", city)
                            ),
                            new ClientEntity(
                                    "Labadie Inc",
                                    "Jana T Kaufmann",
                                    "kaufmann@labadie.de",
                                    "04533 96 51908",
                                    new AddressEntity("Hans-Grade-Allee 41", city)
                            ),
                            new ClientEntity(
                                    "Hackett, Cormier and Nader",
                                    "Michael A Frei",
                                    "frei@hackett.de",
                                    "02682 99 31040",
                                    new AddressEntity("Knesebeckstrasse 117", city2)
                            ),
                            new ClientEntity(
                                    "Kirlin - Gleason",
                                    "Tom A Fleischer",
                                    "fleischer@kirlin-gleason.de",
                                    "09303 14 26465",
                                    new AddressEntity("Billwerder Neuer Deich 76", city3)
                            ),
                            new ClientEntity(
                                    "Weber, Heller and Bauch",
                                    "Sebastian G Maurer",
                                    "maurer@weber.de",
                                    "02691 82 12505",
                                    new AddressEntity("Augsburger Strasse 93", city)
                            ),
                            new ClientEntity(
                                    "Hessel, Hayes and Witting",
                                    "Dieter A Reinhard",
                                    "reinhard@hessel.de",
                                    "07248 80 76559",
                                    new AddressEntity("Sömmeringstr. 21", city)
                            ),
                            new ClientEntity(
                                    "Herman LLC",
                                    "Lisa L Rothschild",
                                    "rothschild@hemanllc.de",
                                    "02688 63 41765",
                                    new AddressEntity("Knesebeckstrasse 52", city)
                            ),
                            new ClientEntity(
                                    "Runolfsdottir - Homenick",
                                    "Philipp A Duerr",
                                    "duerr@runolfsdottor.de",
                                    "09621 72 42107",
                                    new AddressEntity("Flughafenstrasse 46", city2)
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
                                    clientEntities.get(rnd.nextInt(clientEntities.size() - 1)),
                                    LocalDate.of(rnd.nextInt(20) + 1980, rnd.nextInt(11) + 1, 1)
                            )
                    );
                }
            });
        }
    }

    private void createInitialProjectData() {
        if (projectRepository.count() == 0) {
            String[] names = new String[]{"Meta Labs", "The Tech Factory", "Techno Deck",
                    "Techware", "Dotech", "True Solutions", "Solid Tech", "Rich Cast",
                    "Rich Option", "Rich Guru", "Make Rich", "Dotech", "Rich AI",
                    "Hyper Rich", "AI Gear", "Active Labs", "Robo Flow",
                    "The Tech Club", "Techloop", "Zentech", "Solutionic",
                    "Tech Now", "The Virtual Project", "Virtual Storm", "Money Capsule",
                    "Tech Atlas", "Money Graph", "The Money Factory", "Uptech",
                    "Labsus", "Truetechno", "Virtualic", "Microly", "Gear Rise",
                    "Engear", "Enchipica", "Data Money Mine", "Data Push",
                    "Digital Ship", "Digitalmed", "Techism", "Core Money Tech", "The Art of Money",
                    "Coengineering", "Tech Money Loop"};
            String[] descriptions = new String[]{
                    "This project is very important because it brings a lot of money.",
                    "How can we earn more money with this?",
                    "Please work hard, we need to make money.",
                    "Money is better then employee satisfaction!",
                    "If you are not fine with working long hours you are wrong in this project.",
                    "This is one of the most important project, so please make no mistakes!",
                    "Who disappoints this customer will leave forever!",
                    "This project will bring us more money than ever.",
                    "You are fired if you read this because you have more important stuff to do!",
                    "This will make us all rich, I can feel it :)",
                    "My Guru said this project will be great for me.",
                    "If we finish this soon we will have a big party with cocaine and hookers",
                    "The art of money is too complicated for this lowly rabble.",
                    "They all behave like cattle, we are the wolves!",
                    "Where is the money?"
            };
            SecureRandom rnd = new SecureRandom();

            for (String name : names) {
                ProjectEntity projectEntity = new ProjectEntity(
                        name,
                        descriptions[rnd.nextInt(descriptions.length - 1)],
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

        List<EmployeeEntity> developers = employees.stream().filter(employeeEntity -> employeeEntity.getOccupation() == Occupation.DEVELOPER).collect(Collectors.toList());
        List<EmployeeEntity> managers = employees.stream().filter(employeeEntity -> employeeEntity.getOccupation() == Occupation.MANAGEMENT).collect(Collectors.toList());

        projects.forEach(
                projectEntity -> {
                    List.of(managers.get(rnd.nextInt(managers.size() - 1)), developers.get(rnd.nextInt(developers.size() - 1)), employees.get(rnd.nextInt(employees.size() - 1))).forEach(
                            projectEntity::addProjectManager
                    );

                    List.of(clients.get(rnd.nextInt(clients.size() - 1))).forEach(
                            projectEntity::addProjectClient
                    );

                    projectRepository.save(projectEntity);
                }
        );
    }


}

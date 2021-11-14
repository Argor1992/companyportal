package com.company.application.data.client.service;

import com.company.application.data.address.entity.AddressEntity;
import com.company.application.data.client.entity.ClientEntity;
import com.company.application.data.client.repository.ClientRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostConstruct
    public void createInitialData() {
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
                                    "schidt@nadergroup.de",
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
}

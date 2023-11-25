package banque.services;

import banque.dto.DepotDTO;
import banque.dto.OperationDTO;
import banque.modele.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@SpringBootTest
@Transactional
class BanqueServiceTest {


    @Autowired
    private ICompteService compteService;

    @Autowired
    private OperationService operationService;
    @Test
    void listerCompte() {
        List<Compte> comptes =compteService.listerCompte(1).stream().filter(Objects::nonNull).collect(Collectors.toList());
        Assertions.assertEquals(2,comptes.size());
    }

    @Test
    void listerOperationParCompte() {
        List<Compte> comptes =compteService.listerCompte(1).stream().filter(Objects::nonNull).collect(Collectors.toList());
        Compte c =comptes.get(0);
        List<Operation> operationsList = c.getOperations().stream().filter(Objects::nonNull).collect(Collectors.toList());
        Assertions.assertEquals(1,operationsList.size());
        Operation actual =operationsList.get(0);

        Operation expected = new Operation();
        Devise d= new Devise();
        d.setNomDevise("euro");
        expected.setDevise(d);
        Client client = new Client();
        client.setNom("SCHALLER");
        client.setPrenom("Thomas");
        client.setIdentifiant(1);
        expected.setCompte(c);
        expected.setClient(client);
        expected.setMontant(200);
        expected.setTypeOperation(TypeOperation.depot);
        expected.setDateOperation(LocalDateTime.parse("2023-11-17 15:32:01.12", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS")));
        Assertions.assertEquals(expected,actual);
    }
   @Test
    void ajoutDepot()
    {
        List<Compte> comptes =compteService.listerCompte(1).stream().filter(Objects::nonNull).collect(Collectors.toList());
        LocalDateTime dateOperation =LocalDateTime.parse("2023-11-23 11:42:01.19", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS"));
        Compte c =comptes.get(0);
        OperationDTO depot = new DepotDTO();
        depot.setMontant(100);
        Devise d= new Devise();
        d.setNomDevise("euro");
        depot.setDevise(d);
        Client client = new Client();
        client.setNom("SCHALLER");
        client.setIdentifiant(1);
        depot.setCompte(c);
        depot.setClient(client);
        depot.setDateOperation(LocalDateTime.now());
        operationService.applyOperation(1,(long)1,depot);
    }
}
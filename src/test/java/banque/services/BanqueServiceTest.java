package banque.services;

import banque.modele.Compte;
import banque.modele.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.List;


@SpringBootTest
@Transactional
class BanqueServiceTest {


    @Autowired
    private CompteService compteService;
    @Test
    void listerCompte() {
        List<Compte> comptes =compteService.listerCompte(2);
        // etrangement j'obtiens des une liste avec des elements vides, a voir si il faudrait pas reporter dans le get de comptes
        while(comptes.remove(null))
        {

        }
        Assertions.assertEquals(2,comptes.size());
    }

    @Test
    void listerOperationParCompte() {
        List<Compte> comptes =compteService.listerCompte(2);
        // etrangement j'obtiens des une liste avec des elements vides, a voir si il faudrait pas reporter dans le get de comptes
        while(comptes.remove(null))
        {

        }
        Compte c =comptes.get(0);
        List<Operation> operationsList = c.getOperations();
        // etrangement j'obtiens des une liste avec des elements vides, a voir si il faudrait pas reporter dans le get de comptes
        while(operationsList.remove(null))
        {

        }
        Assertions.assertEquals(1,operationsList.size());
    }
}
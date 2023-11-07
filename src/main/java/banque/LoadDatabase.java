package banque;


import banque.modele.*;
import banque.repository.ClientRepository;
import banque.repository.CompteRepository;
import banque.repository.DeviseRepository;
import banque.repository.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Slf4j
@Configuration
public class LoadDatabase {


    @Bean
    CommandLineRunner initDatabase(ClientRepository depotClient, CompteRepository depotCompte, OperationRepository depotOperation, DeviseRepository depotDevise)   {
        return args -> {
            Calendar c= Calendar.getInstance();
            Devise euro = new Devise();
            euro.setNomDevise("euro");
            depotDevise.save(euro);
            Client client = new Client();
            client.setNom("SCHALLER");
            client.setPrenom("Thomas");
            depotClient.save(client);

            Compte compte1 = new Compte();
            compte1.setDevise(euro);
            compte1.setSolde(100);
            compte1.setMinimumAutorise(-100);
            compte1.setProprietaire(client);
            depotCompte.save(compte1);

            Compte compte2 = new Compte();
            compte2.setDevise(euro);
            compte2.setSolde(500);
            compte2.setMinimumAutorise(-240);
            compte2.setProprietaire(client);
            depotCompte.save(compte2);

            Operation depot = new Operation();
            depot.setMontant(200);
            depot.setCompte(compte1);
            depot.setDevise(euro);
            depotOperation.save(depot);
            List<Operation> operations = new ArrayList<>();
            operations.add(depot);
            compte1.setOperations(operations);
            depotCompte.save(compte1);
        };
    }
}

package banque.repository;

import banque.modele.Client;
import banque.modele.Compte;
import banque.modele.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation,Long> {
    List<Operation> findByClient(Client client);
    List<Operation> findByCompte(Compte compte);
}

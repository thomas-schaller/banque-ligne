package banque.services;

import banque.comportement.OperationTraitement;
import banque.modele.Operation;

import java.util.List;

public interface IOperationService {

    void applyOperation(final long idClient, final Long idCompte, OperationTraitement newOperation);

    /**
     * Liste les operations d'un compte d'un client
     * @param idClient
     * @param idCompte
     * @return
     */
    List<Operation> ListerOperationParCompte(long  idClient, long idCompte);

    boolean verifierMontantAutorise(long idCompte, OperationTraitement operation);

}

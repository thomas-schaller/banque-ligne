package banque.services;

import banque.comportement.OperationTraitement;
import banque.modele.Compte;
import banque.modele.Operation;
import banque.repository.CompteRepository;
import banque.repository.OperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OperationService implements IOperationService{

    final private CompteRepository compteRepository;
    final private OperationRepository operationRepository;

    final private CompteService compteService;
    public OperationService(CompteRepository compteRepository, OperationRepository operationRepository,CompteService compteService)
    {
        this.compteRepository=compteRepository;
        this.operationRepository = operationRepository;
        this.compteService= compteService;
    }
    public void applyOperation(final long idClient, final Long idCompte, OperationTraitement newOperation)
    {
        if ( !compteService.verifierAppartenanceCompteClient(idClient,idCompte) ) {
            String messageErreur = "Le compte " +
                    idCompte +
                    " n'appartient pas au client " +
                    idClient;
            throw new IllegalArgumentException(
                    messageErreur);
        }
        if ( !verifierMontantAutorise(idCompte,newOperation))
        {
            String messageErreur = "Le montant " +
                    newOperation.getMontant() +
                    " n'est pas autorisé pour le compte " +
                    idCompte;
            throw new IllegalArgumentException(
                    messageErreur);
        }

        Compte compte = compteRepository.findById(idCompte).orElseThrow();
        newOperation.setCompte(compte);
        //application de l'operation
        newOperation.apply();

        //sauvegarde
        operationRepository.saveAll(newOperation.getOperations());
        compteRepository.save(compte);
    }
    public List<Operation> ListerOperationParCompte(long  idClient, long idCompte)
    {
        Compte compte = compteRepository.findById(idCompte).orElseThrow();
        if ( !compteService.verifierAppartenanceCompteClient(idClient,idCompte))
        {
            String messageErreur = "Le compte " +
                    compte.getIdentifiant() +
                    " n'appartient pas au client " +
                    idClient;
            throw new IllegalArgumentException(
                    messageErreur);
        }
        return compte.getOperations();

    }


    public boolean verifierMontantAutorise(long idCompte, OperationTraitement newOperation)
    {
        Compte compte = compteRepository.findById(idCompte).orElseThrow();
        return compte.getMinimumAutorise() <= compte.getSolde()-newOperation.getMontant();
    }
}

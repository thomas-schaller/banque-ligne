package banque.services;

import banque.dto.OperationDTO;
import banque.modele.*;
import banque.repository.ClientRepository;
import banque.repository.CompteRepository;
import banque.repository.OperationRepository;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BanqueService implements IBanqueService
{
    private final ClientRepository clientRepository ;
    private final CompteRepository compteRepository ;
    private final OperationRepository operationRepository;

    public BanqueService(final ClientRepository clientRepository, CompteRepository compteRepository , OperationRepository operationRepository) {
        this.clientRepository = clientRepository;
        this.compteRepository = compteRepository;
        this.operationRepository = operationRepository;
    }

    public List<Compte> listerCompte(final long idClient)
    {
        Client client = clientRepository.findById(idClient).orElseThrow();
        return client.getPossede();
    }

    public void applyOperation(final long idClient, final Long idCompte, OperationDTO newOperation)
    {
        Client client = clientRepository.findById(idClient).orElseThrow();
        Compte compte = compteRepository.findById(idCompte).orElseThrow();
        List<Operation> resultOperations = newOperation.getOperations();
        if ( verifierAppartenanceCompteClient(client,compte) && verifierMontantAutorice(compte,newOperation)) {

            //application de l'operation
            newOperation.apply();

            //sauvegarde
            for (Operation operation : resultOperations) {
                operationRepository.save(operation);
                compteRepository.save(compte);
            }
        }

    }

    public List<Operation> ListerOperationParCompte(long  idClient, long idCompte)
    {
        Client client = clientRepository.findById(idClient).orElseThrow();
        Compte compte = compteRepository.findById(idCompte).orElseThrow();
        if ( verifierAppartenanceCompteClient(client,compte))
        {
            return compte.getOperations();
        }
        String messageErreur = "Le compte " +
                compte.getIdentifiant() +
                " n'appartient pas au client " +
                client.getIdentifiant();
        throw new InvalidParameterException(
                messageErreur);
    }

    private boolean verifierAppartenanceCompteClient(Client client, Compte compte)
    {
        return client.getPossede().contains(compte);
    }

    private boolean verifierMontantAutorice(Compte compte, OperationDTO newOperation)
    {
        return compte.getMinimumAutorise() <= compte.getSolde()-newOperation.getMontant();
    }
}

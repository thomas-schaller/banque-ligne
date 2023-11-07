package banque.services;

import banque.modele.*;
import banque.repository.ClientRepository;
import banque.repository.CompteRepository;
import banque.repository.OperationRepository;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BanqueService
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

    public Operation depot(final long idClient,final Long idCompte, final double montant)
    {
        Client client = clientRepository.findById(idClient).orElseThrow();
        Compte compte = compteRepository.findById(idCompte).orElseThrow();
        Operation resultOperation = null;
        if ( verifierAppartenanceCompteClient(client,compte)) {
            // Creation et Initialisation Operation depot
            resultOperation= new Depot();
            resultOperation.setMontant(montant);
            resultOperation.setDevise(compte.getDevise());
            resultOperation.setClient(client);
            resultOperation.setCompte(compte);

            // On applique l'opération ainsi créée
            resultOperation.apply();

            // on sauvegarde
            operationRepository.save(resultOperation);

            compteRepository.save(compte);

        }
        return resultOperation;
    }

    public Operation retrait(final long idClient,final Long idCompte, final double montant)
    {
        Client client = clientRepository.findById(idClient).orElseThrow();
        Compte compte = compteRepository.findById(idCompte).orElseThrow();
        Operation resultOperation = null;
        if ( verifierAppartenanceCompteClient(client,compte)) {

            if ( compte.getMinimumAutorise() <= compte.getSolde()-montant) {

                //Creation Operation Retrait
                resultOperation = new Retrait();
                resultOperation.setMontant(montant);
                resultOperation.setDevise(compte.getDevise());
                resultOperation.setClient(client);
                resultOperation.setCompte(compte);

                //application de l'operation
                resultOperation.apply();
                //sauvegarde
                operationRepository.save(resultOperation);

                compteRepository.save(compte);
            }
        }
        return resultOperation;
    }

    public Operation virer(final String nomClient,final Long idCompteOrigine, long idCompteDestinataire,final double montant)
    {
        Client client = clientRepository.findByNom(nomClient);
        Compte compteOri = compteRepository.findById(idCompteOrigine).orElseThrow();
        Compte compteDesti = compteRepository.findById(idCompteDestinataire).orElseThrow();
        Virement resultOperation = null;
        if (verifierAppartenanceCompteClient(client,compteOri) && verifierAppartenanceCompteClient(client,compteDesti)) {

            if ( compteOri.getMinimumAutorise() <= compteOri.getSolde()-montant) {
                //Creation Operation Virement
                resultOperation = new Virement();
                resultOperation.setClient(client);
                resultOperation.setDateOperation(LocalDateTime.now());
                resultOperation.setCompte(compteDesti);

                resultOperation.apply();

                // sauvegarde
                operationRepository.save(resultOperation);
                compteRepository.save(compteOri);
            }
        }
        return resultOperation;
    }

    private boolean verifierAppartenanceCompteClient(Client client, Compte compte)
    {
        return client.getPossede().contains(compte);
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
}

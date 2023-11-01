package banque.services;

import banque.modele.Client;
import banque.modele.Compte;
import banque.modele.Operation;
import banque.modele.TypeOperation;
import banque.repository.ClientRepository;
import banque.repository.CompteRepository;
import banque.repository.OperationRepository;
import org.springframework.stereotype.Service;

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
        if ( client.getPossede().contains(compte)) {;
            List<Compte> listeCompte = new ArrayList<>();
            listeCompte.add(compte);
            resultOperation= new Operation(montant,compte.getDevise());
            resultOperation.setTypeOperation(TypeOperation.depot);
            resultOperation.setClient(client);
            resultOperation.setDateOperation( LocalDateTime.now());
            resultOperation.setComptes(listeCompte);
            operationRepository.save(resultOperation);
            List<Operation> listeOpe= compte.getOperations();
            if (listeOpe == null)
            {
                listeOpe = new ArrayList<>();

            }
            listeOpe.add(resultOperation);
            compte.setOperations(listeOpe);
            compte.setSolde(compte.getSolde()+montant);
            compteRepository.save(compte);

        }
        return resultOperation;
    }

    public Operation retrait(final String nomClient,final Long idCompte, final double montant)
    {
        Client client = clientRepository.findByNom(nomClient);
        Optional<Compte> compte = compteRepository.findById(idCompte);
        Operation resultOperation = null;
        if (client != null && compte.isPresent() && client.getPossede().contains(compte.get())) {
            Compte com= compte.get();
            if ( com.getMinimumAutorise() <= com.getSolde()-montant) {
                List<Compte> listeCompte = new ArrayList<>();
                listeCompte.add(com);
                resultOperation = new Operation(montant, compte.get().getDevise());
                resultOperation.setTypeOperation(TypeOperation.retrait);
                resultOperation.setClient(client);
                resultOperation.setDateOperation(LocalDateTime.now());
                resultOperation.setComptes(listeCompte);
                operationRepository.save(resultOperation);
                List<Operation> listeOpe = com.getOperations();
                if (listeOpe == null) {
                    listeOpe = new ArrayList<>();

                }
                listeOpe.add(resultOperation);
                com.setOperations(listeOpe);
                com.setSolde(com.getSolde() - montant);
                compteRepository.save(com);
            }
        }
        return resultOperation;
    }

    public Operation virer(final String nomClient,final Long idCompteOrigine, long idCompteDestinataire,final double montant)
    {
        Client client = clientRepository.findByNom(nomClient);
        Optional<Compte> compteOri = compteRepository.findById(idCompteOrigine);
        Optional<Compte> compteDesti = compteRepository.findById(idCompteDestinataire);
        Operation resultOperation = null;
        if (client != null && compteOri.isPresent() &&  compteDesti.isPresent() && client.getPossede().contains(compteOri.get()) && client.getPossede().contains(compteDesti.get()) ) {
            Compte comOri= compteOri.get();
            Compte comDesti= compteDesti.get();
            if ( comOri.getMinimumAutorise() <= comOri.getSolde()-montant) {
                List<Compte> listeCompte = new ArrayList<>();
                listeCompte.add(comOri);
                listeCompte.add(comDesti);
                resultOperation = new Operation(montant, compteOri.get().getDevise());
                resultOperation.setTypeOperation(TypeOperation.virement);
                resultOperation.setClient(client);
                resultOperation.setDateOperation(LocalDateTime.now());
                resultOperation.setComptes(listeCompte);
                operationRepository.save(resultOperation);
                List<Operation> listeOpe = comOri.getOperations();
                if (listeOpe == null) {
                    listeOpe = new ArrayList<>();

                }
                listeOpe.add(resultOperation);
                comOri.setOperations(listeOpe);
                comOri.setSolde(comOri.getSolde() - montant);
                compteRepository.save(comOri);
            }
        }
        return resultOperation;
    }

    public List<Operation> ListerOperationParCompte(String nomClient, long idCompte)
    {
        return null;
    }
}

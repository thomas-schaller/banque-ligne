package banque.services;

import banque.modele.Client;
import banque.modele.Compte;
import banque.repository.ClientRepository;
import banque.repository.CompteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteService implements ICompteService
{
    private final ClientRepository clientRepository;
    private final CompteRepository compteRepositoryRepository;
    public CompteService(ClientRepository clientRepository, CompteRepository compteRepository)
    {
        this.clientRepository=clientRepository;
        this.compteRepositoryRepository=compteRepository;
    }

    public List<Compte> listerCompte(final long idClient)
    {
        Client client = clientRepository.findById(idClient).orElseThrow();
        return client.getPossede();
    }

    @Override
    public boolean verifierAppartenanceCompteClient(long idClient, Long idCompte) {
        Client client = clientRepository.findById(idClient).orElseThrow();
        Compte compte = compteRepositoryRepository.findById(idCompte).orElseThrow();
        return client.getPossede().contains(compte);
    }

}

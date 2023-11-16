package banque.services;

import banque.modele.Compte;

import java.util.List;

public interface ICompteService {
    /**
     * Liste les comptes du client
     * @param idClient
     * @return
     */
    List<Compte> listerCompte(final long idClient);

    boolean verifierAppartenanceCompteClient(final long idClient, final Long idCompte);

}

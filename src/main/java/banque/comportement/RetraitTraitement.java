package banque.comportement;

import banque.modele.Compte;
import banque.modele.TypeOperation;

import java.security.InvalidParameterException;

public class RetraitTraitement extends OperationTraitement {

    public RetraitTraitement() {
        setTypeOperation(TypeOperation.retrait);
    }

    @Override
    public void apply() {
        Compte compte = this.getCompte();
        if (compte.getSolde()-getMontant() < compte.getMinimumAutorise())
        {
            compte.setSolde(compte.getSolde() - getMontant());
        }
        else {
            throw new InvalidParameterException("Depassement du seuil minimum Autorisé :"+compte.getMinimumAutorise());
        }
    }
}
